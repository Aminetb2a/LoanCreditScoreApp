package patika.dev.definex.loancreditscore.service.common;

import org.springframework.stereotype.Service;
import patika.dev.definex.loancreditscore.constant.IncomeRange;
import patika.dev.definex.loancreditscore.enums.Operator;


@Service
public class IncomeRangeServiceImpl implements IncomeRangeService {
    @Override
    public Operator getIncomeRange(Double income) {
        if (IncomeRange.LOW.contains(income))
            return Operator.LOW;
        else if (IncomeRange.MEDIUM.contains(income))
            return Operator.MEDIUM;
        return Operator.HIGH;
    }
}
