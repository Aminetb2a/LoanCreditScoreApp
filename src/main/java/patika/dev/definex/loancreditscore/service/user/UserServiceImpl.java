package patika.dev.definex.loancreditscore.service.user;

import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

    /**
     * Method that returns the loan application report using user's ID number
     * and birth date
     *
     * @param idNo      user's ID number
     * @param birthDate user's birth date
     * @return loan application report
     */
    @SneakyThrows
    @Override
    public CreditScore getCreditScoreReport(Long idNo, LocalDate birthDate) {
        //format date
        Date birthdate = dateFormatter.getFormattedDate(birthDate);
        // get user info by idNo and birthdate
        User user = userRepository.findByIdNoAndBirthDate(idNo, birthdate)
                .orElseThrow(() -> new UserNotFoundException(idNo.toString()));
        // return credit score
        return creditScoreMapper.mapToCreditScore(user);
    }

    /**
     * Method that handles the loan application process
     *
     * @param userDTO user's information
     * @return loan application status
     */
    @SneakyThrows
    @Override
    public CreditScore applyToLoan(UserDTO userDTO) {
        // check if user with idNo exists
        if (userRepository.findByIdNo(userDTO.getIdNo()).isPresent())
            throw new UserFoundException(userDTO.getIdNo().toString());
        // process user data -> return credit score
        CreditScore creditScore = creditScoreService.processCreditScore(userDTO);
        userDTO.setCreditLimit(creditScore.getLimit());
        userDTO.setLoanStatus(creditScore.getStatus());
        //send sms to client
        Message smsStatus = smsService.sendSms(userDTO.getPhoneNumber(), smsGenerator.generateSms(userDTO));
        log.debug("Twilio message: " + (smsStatus == null ? "failed to send SMS" : smsStatus.toString()));
        // handle  SMS service failure
        Optional.ofNullable(smsStatus)
                .map(Message::getSid)
                .ifPresent(userDTO::setSmsId);
        // save user records to db
        userRepository.save(mapper.mapToUser(userDTO));
        // return credit score
        return creditScore;
    }

    /**
     * Method that updates user's details
     *
     * @param userDTO user's updated information
     * @return updated user's details
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        // check if user exists
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserNotFoundException(userDTO.getId()));
        // process the credit score with the new inputted data
        CreditScore creditScore = creditScoreService.processCreditScore(userDTO);
        userDTO.setCreditLimit(creditScore.getLimit());
        userDTO.setLoanStatus(creditScore.getStatus());
        // update the user details
        User savedUser = userRepository.save(mapper.mapToUser(user, userDTO));
        // return updated user details
        return mapperDTO.mapToUserDTO(savedUser);
    }

    /**
     * Method that deletes user from db using user's id
     *
     * @param id user's db id
     */
    @Override
    public void deleteUser(String id) {
        //check if user with id exists
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }
}
