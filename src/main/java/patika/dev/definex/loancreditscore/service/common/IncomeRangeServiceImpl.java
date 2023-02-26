package patika.dev.definex.loancreditscore.service.common;

import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.constant.IncomeRange;
import patika.dev.definex.loancreditscore.enums.IncomeCategory;


@Service
public class IncomeRangeServiceImpl implements IncomeRangeService {
    @Override
    public IncomeCategory getIncomeCategory(Double income) {
        if (IncomeRange.LOW.contains(income))
            return IncomeCategory.LOW;
        else if (IncomeRange.MEDIUM.contains(income))
            return IncomeCategory.MEDIUM;
        return IncomeCategory.HIGH;
    }
}
