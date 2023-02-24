package patika.dev.definex.loanCreditScore.service.creditscore.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.definex.loanCreditScore.dto.creditscore.CreditScore;
import patika.dev.definex.loanCreditScore.dto.user.UserDTO;
import patika.dev.definex.loanCreditScore.enums.CreditStatus;
import patika.dev.definex.loanCreditScore.service.creditscore.CreditLimitCalculatorService;
import patika.dev.definex.loanCreditScore.service.creditscore.CreditScoreService;
import patika.dev.definex.loanCreditScore.service.creditscore.CreditStatusService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreditScoreServiceImpl implements CreditScoreService {
    private final CreditStatusService creditStatusService;
    private final CreditLimitCalculatorService creditLimitCalculator;

    @Override
    public CreditScore processCreditScore(UserDTO user) {
        Double creditScore = getCreditScore(user.getIdNo());
        CreditStatus status = creditStatusService.getCreditStatus(creditScore);
        Double limit = status.equals(CreditStatus.REJECTION)
                ? 0.0
                : creditLimitCalculator.getCreditLimit(user, creditScore);
        return CreditScore.builder()
                .status(status)
                .limit(limit)
                .build();
    }

    private Double getCreditScore(Long idNo) {
        return new Random().nextDouble(0.0, 1100.0);
    }
}
