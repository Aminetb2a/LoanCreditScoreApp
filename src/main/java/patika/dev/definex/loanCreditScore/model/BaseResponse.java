package patika.dev.definex.loanCreditScore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"success", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private Boolean success;
    private String message;
    private String field;
    private Object data;
}
