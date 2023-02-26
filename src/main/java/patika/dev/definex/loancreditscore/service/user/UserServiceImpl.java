package patika.dev.definex.loancreditscore.service.user;

import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patika.dev.definex.loancreditscore.config.exception.UserFoundException;
import patika.dev.definex.loancreditscore.config.exception.UserNotFoundException;
import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.creditscore.CreditScoreMapper;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.dto.user.UserDTOMapper;
import patika.dev.definex.loancreditscore.model.user.User;
import patika.dev.definex.loancreditscore.model.user.UserMapper;
import patika.dev.definex.loancreditscore.repository.UserRepository;
import patika.dev.definex.loancreditscore.service.creditscore.CreditScoreService;
import patika.dev.definex.loancreditscore.service.sms.SmsService;
import patika.dev.definex.loancreditscore.service.user.util.SmsGenerator;
import patika.dev.definex.loancreditscore.service.utils.DateFormatter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final UserMapper mapper = new UserMapper();
    private static final UserDTOMapper mapperDTO = new UserDTOMapper();
    private static final CreditScoreMapper creditScoreMapper = new CreditScoreMapper();
    private final SmsService smsService;
    private final SmsGenerator smsGenerator;
    private final DateFormatter dateFormatter;
    private final UserRepository userRepository;
    private final CreditScoreService creditScoreService;

    @SneakyThrows
    @Override
    public CreditScore getCreditScoreReport(Long idNo, LocalDate birthDate) {
        Date birthdate = dateFormatter.getFormattedDate(birthDate);
        User user = userRepository.findByIdNoAndBirthDate(idNo, birthdate)
                .orElseThrow(() -> new UserNotFoundException(idNo.toString()));
        return creditScoreMapper.mapToModel(user);
    }

    @Override
    public CreditScore applyToLoan(UserDTO userDTO) {
        // check if user with idNo exists
        if (userRepository.findByIdNo(userDTO.getIdNo()).isPresent())
            throw new UserFoundException();
        // process user data -> return credit score
        CreditScore creditScore = creditScoreService.processCreditScore(userDTO);
        userDTO.setCreditLimit(creditScore.getLimit());
        userDTO.setCreditStatus(creditScore.getStatus());
        //send sms to client
        Message smsStatus = smsService.sendSms(userDTO.getPhoneNumber(), smsGenerator.generateSms(userDTO));
        // handle  SMS service failure
        Optional.ofNullable(smsStatus)
                .map(Message::getSid)
                .ifPresent(userDTO::setSmsId);
        // save user records to db
        userRepository.save(mapper.mapToUser(userDTO));
        // return credit score
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
        return mapperDTO.mapToModel(savedUser);
    }

    @Override
    public boolean deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return true;
    }
}
