package patika.dev.definex.loanCreditScore.dto.creditscore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import patika.dev.definex.loanCreditScore.enums.CreditStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditScore {
    private Double limit;
    private CreditStatus status;
}
