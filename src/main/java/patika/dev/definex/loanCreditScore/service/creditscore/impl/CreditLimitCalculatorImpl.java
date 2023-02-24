package patika.dev.definex.loanCreditScore.service.creditscore.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.definex.loanCreditScore.constant.CreditScoreConstant;
import patika.dev.definex.loanCreditScore.constant.CreditScoreConstant.CreditScore;
import patika.dev.definex.loanCreditScore.dto.user.UserDTO;
import patika.dev.definex.loanCreditScore.enums.Operator;
import patika.dev.definex.loanCreditScore.service.collateral.CollateralService;
import patika.dev.definex.loanCreditScore.service.common.IncomeRangeService;
import patika.dev.definex.loanCreditScore.service.creditscore.CreditLimitCalculatorService;

import static patika.dev.definex.loanCreditScore.constant.CreditScoreConstant.CreditLimitMultiplier.CREDIT_LIMIT_MULTIPLIER;

@Service
@RequiredArgsConstructor
public class CreditLimitCalculatorImpl implements CreditLimitCalculatorService {
    private final CollateralService collateralService;
    private final IncomeRangeService incomeRangeService;

    @Override
    public Double getCreditLimit(UserDTO user, Double creditScore) {
        Double collateralValue = collateralService.getCollateralValue(user.getCollateralIdNo());
        if (creditScore > CreditScore.HIGH) {
            double limit = user.getIncome() * CREDIT_LIMIT_MULTIPLIER;
            return collateralValue != null ? limit * (collateralValue * CreditScoreConstant.Extra.HIGHER) : limit;
        } else
            return getLimit(user.getIncome(), collateralValue, incomeRangeService.getIncomeRange(user.getIncome()));
    }

    private Double getLimit(Double income, Double collateralValue, Operator operator) {
        return operator.apply(income, collateralValue);
    }
}
