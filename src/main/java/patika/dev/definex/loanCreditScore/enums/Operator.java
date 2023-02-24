package patika.dev.definex.loanCreditScore.enums;

import patika.dev.definex.loanCreditScore.constant.CreditScoreConstant.Extra;
import patika.dev.definex.loanCreditScore.constant.CreditScoreConstant.Limit;

import static patika.dev.definex.loanCreditScore.constant.CreditScoreConstant.CreditLimitMultiplier.CREDIT_LIMIT_MULTIPLIER;

public enum Operator {

    LOW {
        @Override
        public Double apply(Double income, Double collateralValue) {
            return collateralValue != null ? Limit.LOW * (collateralValue * Extra.LOW) : Limit.LOW;
        }
    },


    MEDIUM {
        @Override
        public Double apply(Double income, Double collateralValue) {
            return collateralValue != null ? Limit.MEDIUM * (collateralValue * Extra.MEDIUM) : Limit.MEDIUM;
        }
    },


    HIGH {
        @Override
        public Double apply(Double income, Double collateralValue) {
            Double limit = income * (CREDIT_LIMIT_MULTIPLIER / 2);
            return collateralValue != null ? limit * (collateralValue * Extra.HIGH) : limit;
        }
    };

    public abstract Double apply(Double income, Double collateralValue);
}
