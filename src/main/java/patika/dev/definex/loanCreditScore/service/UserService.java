package patika.dev.definex.loanCreditScore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.model.BaseResponse;
import patika.dev.definex.loanCreditScore.model.User;
import patika.dev.definex.loanCreditScore.model.mapper.UserMapper;
import patika.dev.definex.loanCreditScore.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final UserMapper mapper = new UserMapper();
    private static final UserDTOMapper mapperDTO = new UserDTOMapper();
    private static final BaseResponse result = new BaseResponse();
    private final UserRepository userRepository;

    public BaseResponse createUser(UserDTO userDTO) {
        User user = mapper.mapToUser(userDTO);
        try {
            User response = userRepository.save(user);
            return getSuccess(mapperDTO.mapToModel(response));
        } catch (Exception ex) {
            ex.printStackTrace();
            return getFailure(ex.getMessage());
        }
    }


    private BaseResponse getSuccess(Object data){
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    private BaseResponse getFailure(String message){
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
