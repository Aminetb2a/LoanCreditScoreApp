package patika.dev.definex.loanCreditScore.config.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import patika.dev.definex.loanCreditScore.model.BaseResponse;
import patika.dev.definex.loanCreditScore.model.error.ExceptionModel;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class BaseResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * If the request is not in the expected format, return a 400 Bad Request response with a
     * ValidationError object in the body
     *
     * @param ex      The exception that was thrown
     * @param headers HttpHeaders
     * @param status  The status code to return.
     * @param request The current request.
     * @return A ResponseEntity with a bad request status and a ValidationError object.
     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @Override
//    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return getResponse(ex.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return getResponse(ex.getMessage());
//    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponse(ex.getMessage(), null);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return getResponse(ex.getMessage());
//    }

    /**
     * The MethodArgumentNotValidException handler, return a 400 Bad Request response with a
     * ValidationError object in the body
     *
     * @param ex      The exception that was thrown
     * @param headers HttpHeaders
     * @param status  The HTTP status code to return.
     * @param request The request that triggered the exception
     * @return A list of validation errors.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ExceptionModel> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(violation ->
                        ExceptionModel.builder()
                                .field(violation.getField())
                                .message(violation.getDefaultMessage())
                                .build()).
                collect(toList());

        return getResponse(null, validationErrors);
    }

    private ResponseEntity<Object> getResponse(String message, List<ExceptionModel> exceptions) {
        return ResponseEntity
                .badRequest()
                .body(BaseResponse.builder()
                        .success(false)
                        .message(message)
                        .exception(exceptions)
                        .build()
                );
    }
}
