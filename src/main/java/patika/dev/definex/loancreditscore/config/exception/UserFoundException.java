package patika.dev.definex.loancreditscore.config.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User exists already");
    }
}
