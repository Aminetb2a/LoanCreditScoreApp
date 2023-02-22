package patika.dev.definex.loanCreditScore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.model.User;
import patika.dev.definex.loanCreditScore.model.mapper.UserMapper;
import patika.dev.definex.loanCreditScore.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final UserMapper mapper = new UserMapper();
    private static final UserDTOMapper mapperDTO = new UserDTOMapper();
    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User response = userRepository.save(mapper.mapToUser(userDTO));
        return mapperDTO.mapToModel(response);
    }
}
