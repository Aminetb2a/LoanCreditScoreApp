package patika.dev.definex.loanCreditScore.dto.mapper;

import patika.dev.definex.loanCreditScore.controller.request.UserRequest;
import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.model.User;

public class UserDTOMapper {
    public UserDTO mapToModel(UserRequest userRequest) {
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
