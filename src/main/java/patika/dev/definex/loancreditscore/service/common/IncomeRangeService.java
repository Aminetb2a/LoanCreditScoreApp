package patika.dev.definex.loancreditscore.service.common;

import patika.dev.definex.loancreditscore.enums.IncomeCategory;

public interface IncomeRangeService {
    IncomeCategory getIncomeCategory(Double income);
}
