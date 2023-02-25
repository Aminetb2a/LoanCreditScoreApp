package patika.dev.definex.loancreditscore.service.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.enums.Operator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {IncomeRangeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class IncomeRangeServiceImplTest {
    @Autowired
    private IncomeRangeServiceImpl incomeRangeServiceImpl;

    /**
     * Method under test: {@link IncomeRangeServiceImpl#getIncomeRange(Double)}
     */
    @Test
    void testGetIncomeRange() {
        // Arrange, Act and Assert
        assertEquals(Operator.LOW, incomeRangeServiceImpl.getIncomeRange(0.0d));
        assertEquals(Operator.LOW, incomeRangeServiceImpl.getIncomeRange(4999.99d));
        assertEquals(Operator.MEDIUM, incomeRangeServiceImpl.getIncomeRange(5000.0d));
        assertEquals(Operator.MEDIUM, incomeRangeServiceImpl.getIncomeRange(9999.99d));
        assertEquals(Operator.HIGH, incomeRangeServiceImpl.getIncomeRange(10000.0d));
        assertEquals(Operator.HIGH, incomeRangeServiceImpl.getIncomeRange(Double.NaN));
    }
}

