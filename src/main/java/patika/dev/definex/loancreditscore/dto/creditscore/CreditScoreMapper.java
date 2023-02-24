package patika.dev.definex.loancreditscore.dto.creditscore;

import patika.dev.definex.loancreditscore.model.user.User;

public class CreditScoreMapper {
    public CreditScore mapToModel(User user) {
        return CreditScore.builder()
                .limit(user.getCreditLimit())
                .status(user.getCreditStatus())
                .build();
    }
}
