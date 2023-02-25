package patika.dev.definex.loancreditscore.service.creditscore.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.enums.CreditStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {CreditStatusServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CreditStatusServiceImplTest {
    @Autowired
    private CreditStatusServiceImpl creditStatusServiceImpl;

    /**
     * Method under test: {@link CreditStatusServiceImpl#getCreditStatus(Double)}
     */
    @Test
    void testGetCreditStatus() {
        // Arrange, Act and Assert
        assertEquals(CreditStatus.REJECTED, creditStatusServiceImpl.getCreditStatus(499.99d));
        assertEquals(CreditStatus.APPROVED, creditStatusServiceImpl.getCreditStatus(500.0d));
    }
}

