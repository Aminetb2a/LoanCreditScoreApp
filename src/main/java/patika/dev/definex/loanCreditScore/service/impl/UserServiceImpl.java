package patika.dev.definex.loanCreditScore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.definex.loanCreditScore.config.exception.UserNotFoundException;
import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.model.User;
import patika.dev.definex.loanCreditScore.model.mapper.UserMapper;
import patika.dev.definex.loanCreditScore.repository.UserRepository;
import patika.dev.definex.loanCreditScore.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final UserMapper mapper = new UserMapper();
    private static final UserDTOMapper mapperDTO = new UserDTOMapper();
    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.mapToUser(userDTO);
        User response = userRepository.save(user);
        // TODO return credit score to User
        return mapperDTO.mapToModel(response);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(userDTO.getId()));
        User savedUser = userRepository.save(mapper.mapToUser(user, userDTO));
        // TODO return credit score to User
        return mapperDTO.mapToModel(savedUser);
    }

    public boolean deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return true;
    }
}
