package patika.dev.definex.loanCreditScore.config.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import patika.dev.definex.loanCreditScore.model.BaseResponse;

@RestControllerAdvice
public class BaseResponseBody implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
//        if (returnType.getMethod().getReturnType().isAssignableFrom(ResponseEntity.class)) {
//            return true;
//        }
        boolean bol = !converterType.equals(StringHttpMessageConverter.class);
        return true;
    }

    @Override
    public BaseResponse beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse sResponse) {
        BaseResponse response = BaseResponse.builder()
                .data(data)
                .success(true)
                .build();
        if(HttpMethod.DELETE.equals(request.getMethod()))
            response.setMessage("Deleted Successfully");
        return response;
    }
}
