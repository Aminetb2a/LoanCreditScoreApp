package patika.dev.definex.loancreditscore.config.exception;

/**
 * UserFoundException is a RuntimeException that is thrown when a user exists already.
 */
public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User exists already");
    }
}
