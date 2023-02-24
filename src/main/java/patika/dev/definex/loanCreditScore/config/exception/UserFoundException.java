package patika.dev.definex.loanCreditScore.config.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User exists already");
    }
}
