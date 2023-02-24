package patika.dev.definex.loancreditscore.service.user;

import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;

import java.time.LocalDate;

public interface UserService {
    CreditScore getCreditScoreReport(Long idNo, LocalDate birthDate);

    CreditScore createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    boolean deleteUser(String id);
}
