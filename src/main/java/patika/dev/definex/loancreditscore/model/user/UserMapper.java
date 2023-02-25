package patika.dev.definex.loancreditscore.model.user;

import patika.dev.definex.loancreditscore.dto.user.UserDTO;

public class UserMapper {
    public User mapToUser(UserDTO userDTO) {
        return new User()
                .setIdNo(userDTO.getIdNo())
                .setName(userDTO.getName())
                .setSmsId(userDTO.getSmsId())
                .setIncome(userDTO.getIncome())
                .setSurname(userDTO.getSurname())
                .setBirthDate(userDTO.getBirthDate())
                .setPhoneNumber(userDTO.getPhoneNumber())
                .setCreditLimit(userDTO.getCreditLimit())
                .setCreditStatus(userDTO.getCreditStatus())
                .setCollateralIdNo(userDTO.getCollateralIdNo());
    }

    public User mapToUser(User user, UserDTO userDTO) {
        return user
                .setName(userDTO.getName())
                .setIncome(userDTO.getIncome())
                .setSurname(userDTO.getSurname())
                .setBirthDate(userDTO.getBirthDate())
                .setPhoneNumber(userDTO.getPhoneNumber())
                .setCreditLimit(userDTO.getCreditLimit())
                .setCreditStatus(userDTO.getCreditStatus())
                .setCollateralIdNo(userDTO.getCollateralIdNo());
    }
}
