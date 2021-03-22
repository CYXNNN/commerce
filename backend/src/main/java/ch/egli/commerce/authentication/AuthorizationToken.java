package ch.egli.commerce.authentication;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.Date;
import lombok.Getter;

@Getter
public class AuthorizationToken {

  private String token;

  private String token_type = "Bearer";

  private String subject;

  private String issuer;

  private long issued_at;

  private long expires_at;

  private long expires_in;

  public AuthorizationToken() {
  }

  public AuthorizationToken(final String token, final String subject, final String issuer,
    final Date issuedAt, final Date expiresAt) {
    this.token = notNull(token, "token must not be null");
    this.subject = notNull(subject, "subject must not be null");
    this.issuer = notNull(issuer, "issuer must not be null");
    this.issued_at = notNull(issuedAt, "issuedAt must not be null").getTime();
    this.expires_at = notNull(expiresAt, "expiresAt must not be null").getTime();
    this.expires_in = (expiresAt.getTime() - issuedAt.getTime()) / 1000;
  }

  @Override
  public String toString() {
    return "AuthorizationToken{" +
      "token='" + token + '\'' +
      ", token_type='" + token_type + '\'' +
      ", subject='" + subject + '\'' +
      ", issuer='" + issuer + '\'' +
      ", issued_at=" + issued_at +
      ", expires_at=" + expires_at +
      ", expires_in=" + expires_in +
      "} " + super.toString();
  }
}
