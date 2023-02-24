package patika.dev.definex.loanCreditScore.service.user;

import patika.dev.definex.loanCreditScore.dto.creditscore.CreditScore;
import patika.dev.definex.loanCreditScore.dto.user.UserDTO;

public interface UserService {
    CreditScore createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    boolean deleteUser(String id);
}
