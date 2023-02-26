package patika.dev.definex.loancreditscore.service.creditscore.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.enums.LoanStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {CreditStatusServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CreditStatusServiceImplTest {
    @Autowired
    private CreditStatusServiceImpl creditStatusServiceImpl;

    /**
     * Method under test: {@link CreditStatusServiceImpl#getLoanStatus(Double)}
     */
    @Test
    void testGetCreditStatus() {
        // Arrange, Act and Assert
        assertEquals(LoanStatus.REJECTED, creditStatusServiceImpl.getLoanStatus(499.99d));
        assertEquals(LoanStatus.APPROVED, creditStatusServiceImpl.getLoanStatus(500.0d));
    }
}

