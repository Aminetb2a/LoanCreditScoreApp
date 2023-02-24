package patika.dev.definex.loanCreditScore.config.advice;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import patika.dev.definex.loanCreditScore.config.exception.UserNotFoundException;
import patika.dev.definex.loanCreditScore.model.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse duplicateKeyExceptionHandler(DuplicateKeyException ex) {
        return getResponse(ex.getCause().getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse illegalStateExceptionHandler(IllegalArgumentException ex) {
        return getResponse(ex.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody BaseResponse UserNotFoundExceptionHandler(UserNotFoundException ex) {
        return getResponse(ex.getMessage());
    }

    private BaseResponse getResponse(String message) {
        return BaseResponse.builder()
                .message(message)
                .success(false)
                .build();
    }
}
