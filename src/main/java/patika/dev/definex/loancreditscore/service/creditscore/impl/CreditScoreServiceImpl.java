package patika.dev.definex.loancreditscore.service.creditscore.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.enums.CreditStatus;
import patika.dev.definex.loancreditscore.service.creditscore.CreditLimitCalculatorService;
import patika.dev.definex.loancreditscore.service.creditscore.CreditScoreService;
import patika.dev.definex.loancreditscore.service.creditscore.CreditStatusService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreditScoreServiceImpl implements CreditScoreService {
    private final CreditStatusService creditStatusService;
    private final CreditLimitCalculatorService creditLimitCalculator;

    /**
     * Method that returns the credit status and limit
     *
     * @param user
     * @return
     */
    @Override
    public CreditScore processCreditScore(UserDTO user) {
        Double creditScore = getCreditScore(user.getIdNo());
        CreditStatus status = creditStatusService.getCreditStatus(creditScore);
        Double limit = status.equals(CreditStatus.REJECTED)
                ? 0.0
                : creditLimitCalculator.getCreditLimit(user, creditScore);
        return CreditScore.builder()
                .status(status)
                .limit(limit)
                .build();
    }

    /**
     * Method returns random credit score
     *
     * @param idNo
     * @return Double
     */
    private Double getCreditScore(Long idNo) {
        return new Random().nextDouble(0.0, 1100.0);
    }
}
