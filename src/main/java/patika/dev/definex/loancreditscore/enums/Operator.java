package patika.dev.definex.loancreditscore.enums;

import patika.dev.definex.loancreditscore.constant.CreditScoreConstant.Extra;
import patika.dev.definex.loancreditscore.constant.CreditScoreConstant.Limit;

import static patika.dev.definex.loancreditscore.constant.CreditScoreConstant.CreditLimitMultiplier.CREDIT_LIMIT_MULTIPLIER;

/**
 * The Operator enum handles the credit limit calculation
 * for each type of client depending on their income range
 */
public enum Operator {

    LOW {
        @Override
        public Double apply(Double income, Double guaranteeAmount) {
            return guaranteeAmount != null ? Limit.LOW + (guaranteeAmount * Extra.LOW) : Limit.LOW;
        }
    },


    MEDIUM {
        @Override
        public Double apply(Double income, Double guaranteeAmount) {
            return guaranteeAmount != null ? Limit.MEDIUM + (guaranteeAmount * Extra.MEDIUM) : Limit.MEDIUM;
        }
    },


    HIGH {
        @Override
        public Double apply(Double income, Double guaranteeAmount) {
            Double limit = income * (CREDIT_LIMIT_MULTIPLIER / 2);
            return guaranteeAmount != null ? limit + (guaranteeAmount * Extra.HIGH) : limit;
        }
    };

    public abstract Double apply(Double income, Double guaranteeAmount);
}
