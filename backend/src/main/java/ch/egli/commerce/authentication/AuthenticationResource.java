package ch.egli.commerce.authentication;

import ch.egli.commerce.security.TokenProvider;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.ObjectUtils;

/**
 * A resource that provides an claim based authentication mechanism.
 */
@Path("auth/v1")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AuthenticationResource {

  private static final Logger LOG = Logger.getLogger(AuthenticationResource.class.getName());

  // @Inject
  // private IdentityStoreHandler identityStoreHandler;

  private UserIdentityStore store = new UserIdentityStore();

  @Inject
  private TokenProvider tokenProvider;

  @Inject
  private SecurityContext securityContext;

  @POST
  public Response authenticate(final Credentials credentials) {
    LOG.log(Level.INFO, "Authenticate user {0}", credentials.getUsername());
    CredentialValidationResult result = store.validate(
      new UsernamePasswordCredential(credentials.getUsername(), credentials.getPassword()));

    if (result.getStatus() == CredentialValidationResult.Status.VALID) {
      try {
        AuthorizationToken authorizationToken = tokenProvider.createAuthorizationToken(result);
        LOG.log(Level.INFO, "Authentication successful {0}", authorizationToken);

        return Response.status(Status.OK)
          .entity(authorizationToken)
          .build();
      } catch (JWTCreationException | UnsupportedEncodingException e) {
        LOG.log(Level.SEVERE, "Token creation failed", e);
        return Response.status(Status.UNAUTHORIZED).build();
      }
    } else {
      LOG.log(Level.WARNING, "Wrong credentials for user {0} or user not found",
        credentials.getUsername());
      return Response.status(Status.UNAUTHORIZED).build();
    }
  }

  @GET
  @Path("/{username}/refresh")
  public Response refresh(@PathParam("username") final String username) {
    LOG.log(Level.INFO, "Refresh token for user {0}", username);

    Principal principal = securityContext.getCallerPrincipal();
    if (ObjectUtils.notEqual(principal.getName(), username)) {
      String message = String
        .format("Authenticated %s cannot refresh token for user %s", principal.getName(), username);
      LOG.warning(message);
      return Response.status(Status.UNAUTHORIZED)
        .entity(message)
        .build();
    }

    CredentialValidationResult result = store
      .validate(new CallerOnlyCredential(principal.getName()));
    if (result.getStatus() == CredentialValidationResult.Status.VALID) {
      try {
        AuthorizationToken authorizationToken = tokenProvider.createAuthorizationToken(result);

        LOG.log(Level.INFO, "Refresh token for user {0} successful {1}",
          new Object[]{username, authorizationToken});

        return Response.status(Status.OK)
          .entity(authorizationToken)
          .build();
      } catch (JWTCreationException | UnsupportedEncodingException e) {
        LOG.log(Level.SEVERE, "Token creation failed", e);
        return Response.status(Status.UNAUTHORIZED).build();
      }
    } else {
      LOG.log(Level.WARNING, "Wrong credentials for user {0} or user not found", username);
      return Response.status(Status.UNAUTHORIZED).build();
    }
  }
}
