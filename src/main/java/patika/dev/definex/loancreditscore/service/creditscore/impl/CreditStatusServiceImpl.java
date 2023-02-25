package patika.dev.definex.loancreditscore.service.creditscore.impl;

import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.constant.CreditScoreConstant;
import patika.dev.definex.loancreditscore.enums.CreditStatus;
import patika.dev.definex.loancreditscore.service.creditscore.CreditStatusService;

@Service
public class CreditStatusServiceImpl implements CreditStatusService {
    @Override
    public CreditStatus getCreditStatus(Double creditScore) {
        if (creditScore < CreditScoreConstant.CreditScore.LOW)
            return CreditStatus.REJECTED;
        return CreditStatus.APPROVED;
    }
}
