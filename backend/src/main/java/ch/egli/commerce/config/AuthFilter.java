package ch.egli.commerce.config;

import ch.egli.commerce.security.Token;
import ch.egli.commerce.user.UserService;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthFilter implements ContainerRequestFilter {

  // 401 - Access denied
  private static final Response ACCESS_UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED)
    .entity("Not authorized.").build();

  @Inject
  UserService authService;

  @Context
  private HttpServletRequest request;

  @Context
  private ResourceInfo resourceInfo;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    // Get AuthId and AuthToken from HTTP-Header.
    String authId = requestContext.getHeaderString(Token.PARAM_AUTH_ID);
    String authToken = requestContext.getHeaderString(Token.PARAM_AUTH_TOKEN);

    // Get method invoked.
    Method methodInvoked = resourceInfo.getResourceMethod();

    if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {
      RolesAllowed rolesAllowedAnnotation = methodInvoked.getAnnotation(RolesAllowed.class);
      Set<String> rolesAllowed = new HashSet<>(Arrays.asList(rolesAllowedAnnotation.value()));

      if (!authService.isAuthorized(authId, authToken, rolesAllowed)) {
        requestContext.abortWith(ACCESS_UNAUTHORIZED);
      }
    }
  }
}
