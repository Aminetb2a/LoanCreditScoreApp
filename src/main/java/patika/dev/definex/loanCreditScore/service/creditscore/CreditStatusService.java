package patika.dev.definex.loanCreditScore.service.creditscore;

import patika.dev.definex.loanCreditScore.enums.CreditStatus;

public interface CreditStatusService {
    CreditStatus getCreditStatus(Double creditScore);
}
