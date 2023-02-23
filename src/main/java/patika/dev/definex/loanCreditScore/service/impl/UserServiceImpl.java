package patika.dev.definex.loanCreditScore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.model.User;
import patika.dev.definex.loanCreditScore.model.mapper.UserMapper;
import patika.dev.definex.loanCreditScore.repository.UserRepository;
import patika.dev.definex.loanCreditScore.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final UserMapper mapper = new UserMapper();
    private static final UserDTOMapper mapperDTO = new UserDTOMapper();

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.mapToUser(userDTO);
        User response = userRepository.save(user);
        return mapperDTO.mapToModel(response);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).get();
        User response = userRepository.save(mapper.mapToUser(user, userDTO));
        return mapperDTO.mapToModel(response);
    }
}
