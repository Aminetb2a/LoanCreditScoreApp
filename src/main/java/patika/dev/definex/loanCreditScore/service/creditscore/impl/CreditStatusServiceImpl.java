package patika.dev.definex.loanCreditScore.service.creditscore.impl;

import org.springframework.stereotype.Service;
import patika.dev.definex.loanCreditScore.constant.CreditScoreConstant;
import patika.dev.definex.loanCreditScore.enums.CreditStatus;
import patika.dev.definex.loanCreditScore.service.creditscore.CreditStatusService;

@Service
public class CreditStatusServiceImpl implements CreditStatusService {
    @Override
    public CreditStatus getCreditStatus(Double creditScore) {
        if (creditScore < CreditScoreConstant.CreditScore.LOW)
            return CreditStatus.REJECTION;
        return CreditStatus.APPROVAL;
    }
}
