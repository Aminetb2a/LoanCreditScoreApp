package patika.dev.definex.loanCreditScore.config.advice;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import patika.dev.definex.loanCreditScore.model.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<Object> duplicateKeyExceptionHandler(DuplicateKeyException ex) {
        return getResponse(ex.getCause().getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<Object> illegalStateExceptionHandler(IllegalArgumentException ex) {
        return getResponse(ex.getMessage());
    }

    private ResponseEntity<Object> getResponse(String message) {
        return ResponseEntity
                .badRequest()
                .body(BaseResponse.builder()
                        .message(message)
                        .success(false)
                        .build()
                );
    }
}
