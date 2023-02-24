package patika.dev.definex.loanCreditScore.config.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import patika.dev.definex.loanCreditScore.dto.response.BaseResponse;

@RestControllerAdvice
public class BaseResponseBody implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        String className = returnType.getContainingClass().toString();
        return !className.contains("Exception") && !className.contains("error");
    }

    @Override
    public BaseResponse beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse sResponse) {
        BaseResponse response = BaseResponse.builder()
                .data(data)
                .success(true)
                .build();
        if (HttpMethod.DELETE.equals(request.getMethod()))
            response.setMessage("Deleted Successfully");
        return response;
    }
}
