package patika.dev.definex.loanCreditScore.service.common;

import patika.dev.definex.loanCreditScore.enums.Operator;

public interface IncomeRangeService {
    Operator getIncomeRange(Double income);
}
