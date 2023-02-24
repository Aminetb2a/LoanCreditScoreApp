package patika.dev.definex.loancreditscore.service.creditscore;

import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;

public interface CreditScoreService {
    CreditScore processCreditScore(UserDTO user);
}
