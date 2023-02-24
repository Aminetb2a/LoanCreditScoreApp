package patika.dev.definex.loanCreditScore.service.creditscore;

import patika.dev.definex.loanCreditScore.dto.creditscore.CreditScore;
import patika.dev.definex.loanCreditScore.dto.user.UserDTO;

public interface CreditScoreService {
    CreditScore processCreditScore(UserDTO user);
}
