package patika.dev.definex.loanCreditScore.config.advice;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patika.dev.definex.loanCreditScore.model.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse duplicateKeyExceptionHandler(DuplicateKeyException ex){
        return BaseResponse.builder()
                .message(ex.getCause().getMessage())
                .success(false)
                .build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BaseResponse illegalStateExceptionHandler(IllegalArgumentException ex){
        return BaseResponse.builder()
                .message(ex.getMessage())
                .success(false)
                .build();
    }
}
