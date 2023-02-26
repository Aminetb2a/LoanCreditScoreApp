package patika.dev.definex.loancreditscore.service.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.enums.IncomeCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {IncomeRangeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class IncomeRangeServiceImplTest {
    @Autowired
    private IncomeRangeServiceImpl incomeRangeServiceImpl;

    /**
     * Method under test: {@link IncomeRangeServiceImpl#getIncomeCategory(Double)}
     */
    @Test
    void testGetIncomeRange() {
        // Arrange, Act and Assert
        assertEquals(IncomeCategory.LOW, incomeRangeServiceImpl.getIncomeCategory(0.0d));
        assertEquals(IncomeCategory.LOW, incomeRangeServiceImpl.getIncomeCategory(4999.99d));
        assertEquals(IncomeCategory.MEDIUM, incomeRangeServiceImpl.getIncomeCategory(5000.0d));
        assertEquals(IncomeCategory.MEDIUM, incomeRangeServiceImpl.getIncomeCategory(9999.99d));
        assertEquals(IncomeCategory.HIGH, incomeRangeServiceImpl.getIncomeCategory(10000.0d));
        assertEquals(IncomeCategory.HIGH, incomeRangeServiceImpl.getIncomeCategory(Double.NaN));
    }
}

