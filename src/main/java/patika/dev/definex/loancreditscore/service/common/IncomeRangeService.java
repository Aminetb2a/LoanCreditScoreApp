package patika.dev.definex.loancreditscore.service.common;

import patika.dev.definex.loancreditscore.enums.Operator;

public interface IncomeRangeService {
    Operator getIncomeRange(Double income);
}
