package patika.dev.definex.loancreditscore.service.creditscore;

import patika.dev.definex.loancreditscore.enums.CreditStatus;

public interface CreditStatusService {
    CreditStatus getCreditStatus(Double creditScore);
}
