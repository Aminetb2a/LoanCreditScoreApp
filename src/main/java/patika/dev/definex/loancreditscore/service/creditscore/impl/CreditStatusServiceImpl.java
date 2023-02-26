package patika.dev.definex.loancreditscore.service.creditscore.impl;

import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.constant.CreditScoreConstant;
import patika.dev.definex.loancreditscore.enums.LoanStatus;
import patika.dev.definex.loancreditscore.service.creditscore.LoanStatusService;

@Service
public class CreditStatusServiceImpl implements LoanStatusService {
    /**
     * Method that returns the loan status according to the credit score
     *
     * @param creditScore user's credit score
     * @return LoanStatus Loan's Status
     */
    @Override
    public LoanStatus getLoanStatus(Double creditScore) {
        if (creditScore < CreditScoreConstant.CreditScore.LOW)
            return LoanStatus.REJECTED;
        return LoanStatus.APPROVED;
    }
}
