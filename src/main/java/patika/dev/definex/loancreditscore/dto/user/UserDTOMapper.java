package patika.dev.definex.loancreditscore.dto.user;

import org.springframework.stereotype.Component;
import patika.dev.definex.loancreditscore.dto.request.UserRequestDTO;
import patika.dev.definex.loancreditscore.model.user.User;

@Component
public class UserDTOMapper {
    public UserDTO mapToUserDTO(UserRequestDTO userRequest) {
        return new UserDTO()
                .setId(userRequest.getId())
                .setIdNo(userRequest.getIdNo())
                .setName(userRequest.getName())
                .setIncome(userRequest.getIncome())
                .setSurname(userRequest.getSurname())
                .setBirthDate(userRequest.getBirthDate())
                .setPhoneNumber(userRequest.getPhoneNumber())
                .setCollateralIdNo(userRequest.getCollateralIdNo());
    }

    public UserDTO mapToUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setIdNo(user.getIdNo())
                .setName(user.getName())
                .setIncome(user.getIncome())
                .setSurname(user.getSurname())
                .setBirthDate(user.getBirthDate())
                .setPhoneNumber(user.getPhoneNumber())
                .setCollateralIdNo(user.getCollateralIdNo());
    }
}
