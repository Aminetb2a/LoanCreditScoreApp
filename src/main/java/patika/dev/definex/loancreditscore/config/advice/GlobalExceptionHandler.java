package patika.dev.definex.loancreditscore.config.advice;

import com.twilio.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import patika.dev.definex.loancreditscore.config.exception.UserFoundException;
import patika.dev.definex.loancreditscore.config.exception.UserNotFoundException;
import patika.dev.definex.loancreditscore.dto.response.BaseResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * If a DuplicateKeyException is thrown, return a response with the status code 400 and the message
     * "A user with this information is already registered."
     *
     * @param ex The exception object
     * @return A BaseResponse object with a message.
     */
    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse duplicateKeyExceptionHandler(DuplicateKeyException ex) {
        log.error("DuplicateKeyException: A user with this information is already registered.");
        return getResponse("A user with this information is already registered.");
    }

    /**
     * If an IllegalArgumentException is thrown, return a 400 Bad Request response with the exception
     * message
     *
     * @param ex The exception object
     * @return A BaseResponse object with the message from the exception.
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse illegalStateExceptionHandler(IllegalArgumentException ex) {
        log.error("IllegalArgumentException: " + ex);
        return getResponse(ex.getMessage());
    }

    /**
     * If a UserNotFoundExceptionHandler is thrown, return a 400 Bad Request response with the exception
     * message
     *
     * @param ex The exception object
     * @return A BaseResponse object with the message from the exception.
     */
    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse userNotFoundExceptionHandler(UserNotFoundException ex) {
        return getResponse(ex.getMessage());
    }

    /**
     * If a UserFoundExceptionHandler is thrown, return a 400 Bad Request response with the exception
     * message
     *
     * @param ex The exception object
     * @return A BaseResponse object with the message from the exception.
     */
    @ExceptionHandler({UserFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse userFoundExceptionHandler(UserFoundException ex) {
        return getResponse(ex.getMessage());
    }

    /**
     * If a twilio ApiException is thrown, return a 400 Bad Request response with the exception
     * message
     *
     * @param ex The exception object
     * @return A BaseResponse object with the message from the exception.
     */
    @ExceptionHandler({ApiException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse twilioApiExceptionHandler(ApiException ex) {
        log.error("ApiException: " + ex);
        return getResponse(ex.getMessage());
    }

    /**
     * Method returns a BaseResponse with the exception message.
     *
     * @param message The message that will be displayed to the user.
     * @return A BaseResponse object with a with the exception message
     * and a false success
     */
    private BaseResponse getResponse(String message) {
        return BaseResponse.builder()
                .message(message)
                .success(false)
                .build();
    }
}
