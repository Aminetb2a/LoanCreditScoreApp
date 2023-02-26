package patika.dev.definex.loancreditscore.dto.creditscore;

import patika.dev.definex.loancreditscore.model.user.User;

public class CreditScoreMapper {
    /**
     * Method that takes a User object and maps it to a CreditScore object
     *
     * @param user The user object that is passed in from the controller.
     * @return A CreditScore object
     */
    public CreditScore mapToCreditScore(User user) {
        return CreditScore.builder()
                .limit(user.getCreditLimit())
                .status(user.getCreditStatus())
                .build();
    }
}
