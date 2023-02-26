package patika.dev.definex.loancreditscore.config.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import patika.dev.definex.loancreditscore.dto.response.BaseResponse;

@RestControllerAdvice
public class BaseResponseBody implements ResponseBodyAdvice {

    /**
     * If the class name contains the word "Exception" or "error" or "springdoc", then return false
     * to skip the beforeBodyWrite method
     *
     * @param returnType    The return type of the method that is being called.
     * @param converterType The converter type selected to use. Here, we're ensuring that the converter
     *                      type is not of type ResponseBodyAdvice.
     * @return A boolean value.
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        String className = returnType.getContainingClass().toString();
        return !className.contains("Exception") && !className.contains("error") && !className.contains("springdoc");
    }

    /**
     * Mehtod that takes the response body, wraps it in a BaseResponse object, and returns it
     *
     * @param result                The data that will be returned to the client.
     * @param returnType            The return type of the method that is being invoked.
     * @param selectedContentType   The content type selected through content negotiation.
     * @param selectedConverterType The converter type selected to write to the response body. This
     *                              converter type will be processed unless the method returns void.
     * @param request               The request object
     * @param sResponse             The response object that will be returned to the client.
     * @return A BaseResponse object
     */
    @Override
    public BaseResponse beforeBodyWrite(Object result, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse sResponse) {
        BaseResponse response = BaseResponse.builder()
                .result(result)
                .success(true)
                .build();
        if (HttpMethod.DELETE.equals(request.getMethod()))
            response.setMessage("Deleted Successfully");
        return response;
    }
}
