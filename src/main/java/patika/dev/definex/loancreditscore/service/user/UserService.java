package patika.dev.definex.loancreditscore.service.user;

import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;

public interface UserService {
    CreditScore createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    boolean deleteUser(String id);
}
