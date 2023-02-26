package patika.dev.definex.loancreditscore.config.exception;

/**
 * This class is a RuntimeException that is thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User with ID: " + message + " not found");
    }
}
