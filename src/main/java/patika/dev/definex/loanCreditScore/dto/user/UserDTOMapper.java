package patika.dev.definex.loanCreditScore.dto.user;

import patika.dev.definex.loanCreditScore.dto.request.UserRequestDTO;
import patika.dev.definex.loanCreditScore.model.user.User;

public class UserDTOMapper {
    public UserDTO mapToModel(UserRequestDTO userRequest) {
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

    public UserDTO mapToModel(User user) {
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
