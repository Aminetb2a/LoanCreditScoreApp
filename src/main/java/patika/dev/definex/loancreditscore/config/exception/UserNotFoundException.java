package patika.dev.definex.loancreditscore.config.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User with ID: " + message + " not found");
    }
}
