package patika.dev.definex.loancreditscore.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import patika.dev.definex.loancreditscore.model.error.ExceptionModel;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"success", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private Object result;
    private String message;
    private Boolean success;
    private List<ExceptionModel> exception;
}
