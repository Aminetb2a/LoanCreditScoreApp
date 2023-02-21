package patika.dev.definex.loanCreditScore.model.mapper;

import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.model.User;

public class UserMapper {
    public User mapToUser(UserDTO userDTO) {
        return new User()
                .setIdNo(userDTO.getIdNo())
                .setName(userDTO.getName())
                .setIncome(userDTO.getIncome())
                .setSurname(userDTO.getSurname())
                .setBirthDate(userDTO.getBirthDate())
                .setPhoneNumber(userDTO.getPhoneNumber());
    }
}
