package patika.dev.definex.loancreditscore.service.collateral;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CollateralServiceImpl implements CollateralService {
    private static final Random random = new Random();

    /**
     * Method that returns random collateral value (guarantee amount)
     * if user provided his/her collateral id number
     *
     * @param id user's collateral ID number
     * @return Double
     */
    @Override
    public Double getGuaranteeAmount(Long id) {
        return id == null ? null : 10000.0; // TODO uncomment for tests usage
//        return id == null ? null : random.nextDouble(50000.0, 5000000.0); // TODO uncomment for a better experience
    }
}
