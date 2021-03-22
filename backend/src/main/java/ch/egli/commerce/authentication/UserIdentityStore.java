package ch.egli.commerce.authentication;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;

import ch.egli.commerce.exceptions.UserNotFoundException;
import ch.egli.commerce.exceptions.WrongPasswordException;
import ch.egli.commerce.persistence.User;
import ch.egli.commerce.user.UserService;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

public class UserIdentityStore implements IdentityStore {

  private static final Logger LOGGER = Logger.getLogger(UserIdentityStore.class.getName());

  @Inject
  private UserService userService;

  @Inject
  private AuthenticationService authenticationService;

  @Override
  public CredentialValidationResult validate(Credential credential) {
    if (credential instanceof UsernamePasswordCredential) {
      String caller = ((UsernamePasswordCredential) credential).getCaller();
      try {
        User user = authenticationService
          .authenticate(caller, ((UsernamePasswordCredential) credential).getPasswordAsString());

        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().toString());

        return new CredentialValidationResult(caller, roles);
      } catch (UserNotFoundException e) {
        LOGGER.log(Level.WARNING, "User {0} not found", caller);
      } catch (WrongPasswordException e) {
        LOGGER.log(Level.WARNING, "Wrong password for user {0}", caller);
      }
      return INVALID_RESULT;
    }

    if (credential instanceof CallerOnlyCredential) {
      String caller = ((CallerOnlyCredential) credential).getCaller();
      try {
        User user = userService.find(caller);
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().toString());

        return new CredentialValidationResult(caller, roles);
      } catch (UserNotFoundException e) {
        LOGGER.log(Level.WARNING, "User {0} not found", caller);
        return INVALID_RESULT;
      }
    }

    return NOT_VALIDATED_RESULT;
  }
}
