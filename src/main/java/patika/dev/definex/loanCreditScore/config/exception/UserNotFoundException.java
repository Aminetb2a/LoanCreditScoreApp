package patika.dev.definex.loanCreditScore.config.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User with ID: " + message + " not found");
    }
}
