package patika.dev.definex.loancreditscore.config.advice;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import patika.dev.definex.loancreditscore.dto.response.BaseResponse;
import patika.dev.definex.loancreditscore.model.error.ExceptionModel;

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
     * @return A ResponseEntity with a bad request status and the exception details message.
     */

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return getResponse(ex.getMessage(), null);
    }

    /**
     * This method is called when the request body is not readable
     *
     * @param ex      The exception that was thrown
     * @param headers HttpHeaders
     * @param status  The HTTP status code to return.
     * @param request The current request.
     * @return A ResponseEntity with a bad request status and the exception details message.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return getResponse(ex.getMessage(), null);
    }


    /**
     * A method that handles the HttpRequestMethodNotSupportedException.
     *
     * @param ex      The exception that was thrown
     * @param headers The headers that would have been sent in the response.
     * @param status  The HTTP status code to return.
     * @param request The current request.
     * @return A ResponseEntity with a bad request status and the exception details message.
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return getResponse(ex.getMessage(), null);
    }

    /**
     * A method that handles the MethodArgumentNotValidException.
     *
     * @param ex      The exception that was thrown
     * @param headers HttpHeaders
     * @param status  The HTTP status code to return.
     * @param request The request that triggered the exception
     * @return A ResponseEntity with a bad request status and a list of validation errors.
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ExceptionModel> validationErrors = ex.getBindingResult().getFieldErrors().stream().map(violation -> ExceptionModel.builder().field(violation.getField()).message(violation.getDefaultMessage()).build()).collect(toList());

        return getResponse(null, validationErrors);
    }

    /**
     * Method returns a bad request response with the exception message or a list of validation errors.
     *
     * @param message    The message that will be displayed to the user.
     * @param exceptions A list of ExceptionModel objects.
     * @return A ResponseEntity object with a bad request status and a body containing a BaseResponse
     * object.
     */
    private ResponseEntity<Object> getResponse(String message, List<ExceptionModel> exceptions) {
        return ResponseEntity.badRequest().body(BaseResponse.builder().success(false).message(message).exception(exceptions).build());
    }
}
