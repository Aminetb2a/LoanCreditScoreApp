package patika.dev.definex.loanCreditScore.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"message", "field"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionModel {
    private String message;
    private String field;
}
