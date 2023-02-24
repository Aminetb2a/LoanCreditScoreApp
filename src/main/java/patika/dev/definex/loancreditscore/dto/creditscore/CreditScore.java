package patika.dev.definex.loancreditscore.dto.creditscore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import patika.dev.definex.loancreditscore.enums.CreditStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditScore {
    private Double limit;
    private CreditStatus status;
}
