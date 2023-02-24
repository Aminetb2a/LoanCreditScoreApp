package patika.dev.definex.loanCreditScore.constant;

public interface CreditScoreConstant {
    interface Extra {
        Double LOW = 0.1;
        Double MEDIUM = 0.2;
        Double HIGH = 0.25;
        Double HIGHER = 0.5;
    }

    interface CreditScore {
        Double LOW = 500.0;
        Double HIGH = 1000.0;
    }

    interface Limit {
        Double LOW = 10000.0;
        Double MEDIUM = 20000.0;
    }

    interface CreditLimitMultiplier {
        Double CREDIT_LIMIT_MULTIPLIER = 4.0;
    }
}
