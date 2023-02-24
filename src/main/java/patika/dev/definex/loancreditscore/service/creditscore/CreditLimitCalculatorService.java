package patika.dev.definex.loancreditscore.service.creditscore;

import patika.dev.definex.loancreditscore.dto.user.UserDTO;

public interface CreditLimitCalculatorService {
    Double getCreditLimit(UserDTO user, Double creditScore);
}
