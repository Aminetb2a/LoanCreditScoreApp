package patika.dev.definex.loancreditscore.constant;

import org.apache.commons.lang3.Range;

public interface IncomeRange {
    Range<Double> LOW = Range.between(0.0, 4999.999d);
    Range<Double> MEDIUM = Range.between(5000.0, 9999.999);
    Range<Double> HIGH = Range.between(10000.0, Double.MAX_VALUE);
}
