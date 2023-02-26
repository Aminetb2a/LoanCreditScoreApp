package patika.dev.definex.loancreditscore.config.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * UserFoundException is a RuntimeException that is thrown when a user exists already.
 */
@Slf4j
public class UserFoundException extends RuntimeException {
    public UserFoundException(String idNo) {
        super("User exists already");
        log.error("User with IdNo: " + idNo + " exists already");
    }
}
