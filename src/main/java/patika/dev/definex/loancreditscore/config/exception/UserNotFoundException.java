package patika.dev.definex.loancreditscore.config.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is a RuntimeException that is thrown when a user is not found.
 */
@Slf4j
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User with ID: " + message + " not found");
        log.error("User with ID: " + message + " not found");
    }
}
