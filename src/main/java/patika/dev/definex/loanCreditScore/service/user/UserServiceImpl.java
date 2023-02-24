package patika.dev.definex.loanCreditScore.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patika.dev.definex.loanCreditScore.config.exception.UserFoundException;
import patika.dev.definex.loanCreditScore.config.exception.UserNotFoundException;
import patika.dev.definex.loanCreditScore.dto.creditscore.CreditScore;
import patika.dev.definex.loanCreditScore.dto.user.UserDTO;
import patika.dev.definex.loanCreditScore.dto.user.UserDTOMapper;
import patika.dev.definex.loanCreditScore.model.user.User;
import patika.dev.definex.loanCreditScore.model.user.UserMapper;
import patika.dev.definex.loanCreditScore.repository.UserRepository;
import patika.dev.definex.loanCreditScore.service.creditscore.CreditScoreService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final UserMapper mapper = new UserMapper();
    private static final UserDTOMapper mapperDTO = new UserDTOMapper();
    private final UserRepository userRepository;
    private final CreditScoreService creditScoreService;

    @Override
    public CreditScore createUser(UserDTO userDTO) {
        // check if user exists
        if (userRepository.exists(Example.of((mapper.mapToUser(userDTO)))))
            throw new UserFoundException();
        // process user data -> return credit score
        CreditScore creditScore = creditScoreService.processCreditScore(userDTO);
        userDTO.setCreditLimit(creditScore.getLimit());
        userDTO.setCreditStatus(creditScore.getStatus());
        userRepository.save(mapper.mapToUser(userDTO));
        // TODO send sms
        return creditScore;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        // check if user exists
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserNotFoundException(userDTO.getId()));
        // process the credit score with the new inputted data
        CreditScore creditScore = creditScoreService.processCreditScore(userDTO);
        userDTO.setCreditLimit(creditScore.getLimit());
        userDTO.setCreditStatus(creditScore.getStatus());
        // update the user details
        User savedUser = userRepository.save(mapper.mapToUser(user, userDTO));
        // TODO return credit score to User instead of user data
        return mapperDTO.mapToModel(savedUser);
    }

    @Override
    public boolean deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return true;
    }
}
