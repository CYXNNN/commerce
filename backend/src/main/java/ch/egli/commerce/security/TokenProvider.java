package ch.egli.commerce.security;

import ch.egli.commerce.authentication.AuthorizationToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.CredentialValidationResult;

@ApplicationScoped
public class TokenProvider {

  private String secretKey;
  private long tokenValidity;

  @PostConstruct
  public void init() {
    this.secretKey = "verysecretsecretkey";
    this.tokenValidity = 10;
  }

  public AuthorizationToken createAuthorizationToken(final CredentialValidationResult result)
    throws JWTCreationException, UnsupportedEncodingException {
    LocalDateTime now = LocalDateTime.now();
    Date issuedAt = toDate(now);
    Date expiresAt = toDate(now.plus(tokenValidity, ChronoUnit.MINUTES));
    Date notBefore = toDate(now);

    String issuer = "http://localhost:8080/jaxrs-security-jwt";
    String subject = result.getCallerPrincipal().getName();
    String token = issueToken(issuer, issuedAt, expiresAt, notBefore, subject,
      result.getCallerGroups());

    return new AuthorizationToken(token, subject, issuer, issuedAt, expiresAt);
  }

  public DecodedJWT verifyAndDecodeJwt(String token) {
    return JWT.require(Algorithm.HMAC256(secretKey))
      .withIssuer("http://localhost:8080/jaxrs-security-jwt")
      .acceptLeeway(1)
      .acceptExpiresAt(5)
      .build()
      .verify(token);
  }

  private Date toDate(final LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  private String issueToken(final String issuer, final Date issuedAt, final Date expiresAt,
    final Date notBefore, final String subject, final Set<String> roles)
    throws JWTCreationException, UnsupportedEncodingException {
    return JWT.create()
      .withIssuer(issuer)
      .withIssuedAt(issuedAt)
      .withExpiresAt(expiresAt)
      .withNotBefore(notBefore)
      .withSubject(subject)
      .withArrayClaim("roles", roles.toArray(new String[0]))
      .sign(Algorithm.HMAC256(secretKey));
  }
}
