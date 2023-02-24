package patika.dev.definex.loanCreditScore.service.creditscore;

import patika.dev.definex.loanCreditScore.dto.user.UserDTO;

public interface CreditLimitCalculatorService {
    Double getCreditLimit(UserDTO user, Double creditScore);
}
