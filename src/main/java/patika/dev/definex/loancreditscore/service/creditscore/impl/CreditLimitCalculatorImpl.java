package patika.dev.definex.loancreditscore.service.creditscore.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.constant.CreditScoreConstant;
import patika.dev.definex.loancreditscore.constant.CreditScoreConstant.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.enums.Operator;
import patika.dev.definex.loancreditscore.service.collateral.CollateralService;
import patika.dev.definex.loancreditscore.service.common.IncomeRangeService;
import patika.dev.definex.loancreditscore.service.creditscore.CreditLimitCalculatorService;

import static patika.dev.definex.loancreditscore.constant.CreditScoreConstant.CreditLimitMultiplier.CREDIT_LIMIT_MULTIPLIER;

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
