package patika.dev.definex.loanCreditScore.service;

import patika.dev.definex.loanCreditScore.dto.UserDTO;

public interface UserService { // TODO look for best structure practice
    public UserDTO createUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
}
