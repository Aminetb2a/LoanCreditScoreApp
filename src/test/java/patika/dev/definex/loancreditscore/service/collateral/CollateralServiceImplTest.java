package patika.dev.definex.loancreditscore.service.collateral;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {CollateralServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CollateralServiceImplTest {
    @Autowired
    private CollateralServiceImpl collateralServiceImpl;

    /**
     * Method under test: {@link CollateralServiceImpl#getGuaranteeAmount(Long)}
     */
    @Test
    void testGetCollateralRandomValue() {
        // TODO: Complete this test when service available

        // Arrange and Act
        collateralServiceImpl.getGuaranteeAmount(10000L);
    }

    /**
     * Method under test: {@link CollateralServiceImpl#getGuaranteeAmount(Long)}
     */
    @Test
    void testGetCollateralNullValue() {
        // Arrange, Act and Assert
        assertNull(collateralServiceImpl.getGuaranteeAmount(null));
    }

    /**
     * Method under test: {@link CollateralServiceImpl#getGuaranteeAmount(Long)}
     */
    @Test
    void testGetCollateralFixedValue() {
        // Arrange, Act and Assert
        assertEquals(10000.0d, collateralServiceImpl.getGuaranteeAmount(1L).doubleValue());
    }
}

