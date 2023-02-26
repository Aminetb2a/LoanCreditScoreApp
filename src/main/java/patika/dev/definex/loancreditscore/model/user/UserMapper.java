package patika.dev.definex.loancreditscore.model.user;

import patika.dev.definex.loancreditscore.dto.user.UserDTO;

public class UserMapper {
    /**
     * Method that takes a UserDTO object and maps it to a new User object
     *
     * @param userDTO The userDTO object.
     * @return A User object
     */
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
                .setLoanStatus(userDTO.getLoanStatus())
                .setCollateralIdNo(userDTO.getCollateralIdNo());
    }

    /**
     * Method that takes User and UserDTO objects and maps it to the User object
     *
     * @param user The user object.
     * @param user The userDTO object.
     * @return A User object
     */
    public User mapToUser(User user, UserDTO userDTO) {
        return user
                .setName(userDTO.getName())
                .setIncome(userDTO.getIncome())
                .setSurname(userDTO.getSurname())
                .setBirthDate(userDTO.getBirthDate())
                .setPhoneNumber(userDTO.getPhoneNumber())
                .setCreditLimit(userDTO.getCreditLimit())
                .setLoanStatus(userDTO.getLoanStatus())
                .setCollateralIdNo(userDTO.getCollateralIdNo());
    }
}
