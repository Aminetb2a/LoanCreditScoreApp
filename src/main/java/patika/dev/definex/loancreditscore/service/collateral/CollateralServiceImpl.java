package patika.dev.definex.loancreditscore.service.collateral;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CollateralServiceImpl implements CollateralService {
    private static final Random random = new Random();

    @Override
    public Double getCollateralValue(Long id) {
        return id == null ? null : random.nextDouble(50000.0, 5000000.0);
    }
}
