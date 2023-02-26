package patika.dev.definex.loancreditscore.service.creditscore.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.constant.CreditScoreConstant;
import patika.dev.definex.loancreditscore.constant.CreditScoreConstant.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.enums.IncomeCategory;
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
        Double guaranteeAmount = collateralService.getGuaranteeAmount(user.getCollateralIdNo());
        if (creditScore > CreditScore.HIGH) {
            double limit = user.getIncome() * CREDIT_LIMIT_MULTIPLIER;
            return guaranteeAmount != null ? limit + (guaranteeAmount * CreditScoreConstant.Extra.HIGHER) : limit;
        } else
            return getCreditLimit(user.getIncome(), guaranteeAmount, incomeRangeService.getIncomeCategory(user.getIncome()));
    }

    private Double getCreditLimit(Double income, Double guaranteeAmount, IncomeCategory incomeCategory) {
        return incomeCategory.getCreditLimit(income, guaranteeAmount);
    }
}
