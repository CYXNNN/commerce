package ch.egli.commerce.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {

  public static final String PARAM_AUTH_ID = "auth-id";
  public static final String PARAM_AUTH_TOKEN = "auth-token";

  private String authId;
  private String authToken;
  private String authPermission;

  public Token() {
  }

  public Token(String authId, String authToken, String authPermission) {
    this.authId = authId;
    this.authToken = authToken;
    this.authPermission = authPermission;
  }
}
