package ch.egli.commerce.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

@ApplicationScoped
public class JwtAuthenticationMechanism implements HttpAuthenticationMechanism {

  private static final Logger LOGGER = Logger.getLogger(JwtAuthenticationMechanism.class.getName());
  private static final Pattern PATTERN_AUTHORIZATION_HEADER = Pattern
    .compile("^Bearer [a-zA-Z0-9\\-_\\.]+$", Pattern.CASE_INSENSITIVE);

  @Inject
  private TokenProvider tokenProvider;

  @Override
  public AuthenticationStatus validateRequest(HttpServletRequest request,
    HttpServletResponse response, HttpMessageContext context) {
    LOGGER.log(Level.INFO, "validateRequest: {0}", request.getRequestURI());

    if (!context.isProtected()) {
      // unprotected api call
      return context.doNothing();
    }

    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null) {
      LOGGER.log(Level.WARNING, "Authorization header is missing");
      return context.responseUnauthorized();
    }

    if (!isValidAuthorizationHeader(header)) {
      LOGGER.log(Level.WARNING, "Authorization header is invalid");
      return context.responseUnauthorized();
    }

    try {
      String[] headerComponents = header.split(" ");
      String token = headerComponents[1];

      DecodedJWT jwt = tokenProvider.verifyAndDecodeJwt(token);

      String username = jwt.getSubject();
      List<String> roles = jwt.getClaim("roles").asList(String.class);

      return context.notifyContainerAboutLogin(username, new HashSet<>(roles));
    } catch (JWTVerificationException e) {
      LOGGER.log(Level.WARNING, "JWT token verification failed", e);
    }

    return context.responseUnauthorized();
  }

  private boolean isValidAuthorizationHeader(final String header) {
    return PATTERN_AUTHORIZATION_HEADER.matcher(header).matches();
  }
}
