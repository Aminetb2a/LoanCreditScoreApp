package patika.dev.definex.loanCreditScore.service.common;

import org.springframework.stereotype.Service;
import patika.dev.definex.loanCreditScore.constant.IncomeRange;
import patika.dev.definex.loanCreditScore.enums.Operator;


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
