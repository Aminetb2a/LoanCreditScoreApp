package patika.dev.definex.loancreditscore.service.user;

import com.twilio.rest.api.v2010.account.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.config.exception.UserFoundException;
import patika.dev.definex.loancreditscore.config.exception.UserNotFoundException;
import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.enums.LoanStatus;
import patika.dev.definex.loancreditscore.model.user.User;
import patika.dev.definex.loancreditscore.repository.UserRepository;
import patika.dev.definex.loancreditscore.service.creditscore.CreditScoreService;
import patika.dev.definex.loancreditscore.service.sms.SmsService;
import patika.dev.definex.loancreditscore.service.user.util.SmsGenerator;
import patika.dev.definex.loancreditscore.service.utils.DateFormatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplIntegrationTest {
    @MockBean
    private CreditScoreService creditScoreService;

    @MockBean
    private DateFormatter dateFormatter;

    @MockBean
    private SmsGenerator smsGenerator;

    @MockBean
    private SmsService smsService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#getCreditScoreReport(Long, LocalDate)}
     */
    @Test
    void testSuccessfulGetCreditScoreReport() throws ParseException {
        // Arrange
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(dateFormatter.getFormattedDate(any()))
                .thenReturn(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        user.setCollateralIdNo(1L);
        user.setCreditLimit(10.0d);
        user.setLoanStatus(LoanStatus.APPROVED);
        user.setId("42");
        user.setIdNo(1L);
        user.setIncome(10.0d);
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setSmsId("42");
        user.setSurname("Doe");
        user.setVersion(1L);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByIdNoAndBirthDate(any(), any())).thenReturn(ofResult);

        // Act
        CreditScore actualCreditScoreReport = userServiceImpl.getCreditScoreReport(1L, LocalDate.ofEpochDay(1L));

        // Assert
        assertEquals(10.0d, actualCreditScoreReport.getLimit().doubleValue());
        assertEquals(LoanStatus.APPROVED, actualCreditScoreReport.getStatus());
        verify(dateFormatter).getFormattedDate(any());
        verify(userRepository).findByIdNoAndBirthDate(any(), any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getCreditScoreReport(Long, LocalDate)}
     */
    @Test
    void testGetCreditScoreReportUserNotFoundException() throws ParseException {
        // Arrange
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(dateFormatter.getFormattedDate(any()))
                .thenReturn(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        when(userRepository.findByIdNoAndBirthDate(any(), any())).thenThrow(new UserNotFoundException("User not found"));

        // Act and Assert
        assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.getCreditScoreReport(1L, LocalDate.ofEpochDay(1L)));
        verify(dateFormatter).getFormattedDate(any());
        verify(userRepository).findByIdNoAndBirthDate(any(), any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getCreditScoreReport(Long, LocalDate)}
     */
    @Test
    void testGetCreditScoreReportWithWrongUserDetails() throws ParseException {
        // Arrange
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(dateFormatter.getFormattedDate(any()))
                .thenReturn(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        when(userRepository.findByIdNoAndBirthDate(any(), any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.getCreditScoreReport(1L, LocalDate.ofEpochDay(1L)));
        verify(dateFormatter).getFormattedDate(any());
        verify(userRepository).findByIdNoAndBirthDate(any(), any());
    }

    /**
     * Method under test: {@link UserServiceImpl#applyToLoan(UserDTO)}
     */
    @Test
    void testApplyToLoanWithExistingUser() {
        // Arrange
        User user = new User();
        user.setCollateralIdNo(1L);
        user.setId("42");
        user.setIdNo(1L);
        user.setIncome(10.0d);
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByIdNo(any())).thenReturn(ofResult);

        UserDTO userDTO = new UserDTO();
        userDTO.setIdNo(user.getIdNo());
        userDTO.setIncome(user.getIncome());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());

        // Act and Assert
        assertThrows(UserFoundException.class, () -> userServiceImpl.applyToLoan(userDTO));
        verify(userRepository).findByIdNo(any());
        verify(userRepository, never()).save(any());
    }

    /**
     * Method under test: {@link UserServiceImpl#applyToLoan(UserDTO)}
     */
    @Test
    void testApplyToLoanWithApproval() {
        // Arrange
        when(smsGenerator.generateSms(any())).thenReturn("Generate Sms");
        when(smsService.sendSms(any(), any())).thenReturn(mock(Message.class));

        UserDTO userDTO = new UserDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setCollateralIdNo(1L);
        userDTO.setCreditLimit(10.0d);
        userDTO.setLoanStatus(LoanStatus.APPROVED);
        userDTO.setIdNo(1L);
        userDTO.setIncome(10.0d);
        userDTO.setName("Name");
        userDTO.setPhoneNumber("6625550144");
        userDTO.setSurname("Doe");

        User user = new User();
        user.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        user.setCollateralIdNo(userDTO.getCollateralIdNo());
        user.setCreditLimit(userDTO.getCreditLimit());
        user.setLoanStatus(userDTO.getLoanStatus());
        user.setIdNo(userDTO.getIdNo());
        user.setIncome(userDTO.getIncome());
        user.setName(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setSmsId("smsId");
        user.setSurname(userDTO.getSurname());

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByIdNo(any())).thenReturn(Optional.empty());
        CreditScore creditScore = mock(CreditScore.class);
        when(creditScore.getLimit()).thenReturn(10.0d);
        when(creditScore.getStatus()).thenReturn(LoanStatus.APPROVED);
        when(creditScoreService.processCreditScore(any())).thenReturn(creditScore);

        // Act
        userServiceImpl.applyToLoan(userDTO);

        // Assert
        verify(smsService).sendSms(any(), any());
        verify(smsGenerator).generateSms(any());
        verify(userRepository).save(any());
        verify(userRepository).findByIdNo(any());
        verify(creditScoreService).processCreditScore(any());
        verify(creditScore).getLimit();
        verify(creditScore).getStatus();
        assertNull(userDTO.getSmsId());
        assertEquals(LoanStatus.APPROVED, userDTO.getLoanStatus());
        assertEquals(10.0d, userDTO.getCreditLimit().doubleValue());
    }

    /**
     * Method under test: {@link UserServiceImpl#applyToLoan(UserDTO)}
     */
    @Test
    void testApplyToLoanWithUserCreditScoreNotFound() {
        // Arrange

        UserDTO userDTO = new UserDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setCollateralIdNo(1L);
        userDTO.setCreditLimit(10.0d);
        userDTO.setLoanStatus(LoanStatus.APPROVED);
        userDTO.setIdNo(1L);
        userDTO.setIncome(10.0d);
        userDTO.setName("Name");
        userDTO.setPhoneNumber("6625550144");
        userDTO.setSurname("Doe");

        when(userRepository.findByIdNo(any())).thenReturn(Optional.empty());
        CreditScore creditScore = mock(CreditScore.class);
        when(creditScore.getLimit()).thenThrow(new UserNotFoundException("User not found"));
        when(creditScore.getStatus()).thenThrow(new UserNotFoundException("User not found"));
        when(creditScoreService.processCreditScore(any())).thenReturn(creditScore);

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.applyToLoan(userDTO));
        verify(userRepository).findByIdNo(any());
        verify(creditScoreService).processCreditScore(any());
        verify(creditScore).getLimit();
        verify(creditScore, never()).getStatus();
        verify(userRepository, never()).save(any());
        verify(smsService, never()).sendSms(any(), any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UserDTO)}
     */
    @Test
    void testUpdateExistingUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setCollateralIdNo(1L);
        userDTO.setCreditLimit(10.0d);
        userDTO.setLoanStatus(LoanStatus.APPROVED);
        userDTO.setId("42");
        userDTO.setIdNo(1L);
        userDTO.setIncome(10.0d);
        userDTO.setName("Name");
        userDTO.setPhoneNumber("6625550144");
        userDTO.setSurname("Doe");

        User user = new User();
        user.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        user.setCollateralIdNo(userDTO.getCollateralIdNo());
        user.setCreditLimit(userDTO.getCreditLimit());
        user.setLoanStatus(userDTO.getLoanStatus());
        user.setId(userDTO.getId());
        user.setIdNo(userDTO.getIdNo());
        user.setIncome(userDTO.getIncome());
        user.setName(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setSmsId(userDTO.getSmsId());
        user.setSurname(userDTO.getSurname());

        Optional<User> ofResult = Optional.of(user);

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findById(any())).thenReturn(ofResult);
        CreditScore creditScore = mock(CreditScore.class);
        when(creditScore.getLimit()).thenReturn(10.0d);
        when(creditScore.getStatus()).thenReturn(LoanStatus.APPROVED);
        when(creditScoreService.processCreditScore(any())).thenReturn(creditScore);

        // Act
        UserDTO actualUpdateUserResult = userServiceImpl.updateUser(userDTO);

        // Assert
        assertEquals("Doe", actualUpdateUserResult.getSurname());
        assertEquals("6625550144", actualUpdateUserResult.getPhoneNumber());
        assertEquals("Name", actualUpdateUserResult.getName());
        assertEquals(10.0d, actualUpdateUserResult.getIncome().doubleValue());
        assertEquals(1L, actualUpdateUserResult.getIdNo().longValue());
        assertEquals("42", actualUpdateUserResult.getId());
        assertEquals(1L, actualUpdateUserResult.getCollateralIdNo().longValue());
        verify(userRepository).save(any());
        verify(userRepository).findById(any());
        verify(creditScoreService).processCreditScore(any());
        verify(creditScore).getLimit();
        verify(creditScore).getStatus();
        assertEquals(LoanStatus.APPROVED, userDTO.getLoanStatus());
        assertEquals(10.0d, userDTO.getCreditLimit().doubleValue());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UserDTO)}
     */
    @Test
    void testUpdateNotExistingUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setCollateralIdNo(1L);
        userDTO.setCreditLimit(10.0d);
        userDTO.setLoanStatus(LoanStatus.APPROVED);
        userDTO.setId("42");
        userDTO.setIdNo(1L);
        userDTO.setIncome(10.0d);
        userDTO.setName("Name");
        userDTO.setPhoneNumber("6625550144");
        userDTO.setSurname("Doe");

        when(userRepository.findById(any())).thenThrow(new UserNotFoundException("User not found"));

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.updateUser(userDTO));
        verify(creditScoreService, never()).processCreditScore(any());
        verify(userRepository, never()).save(any());

    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(String)}
     */
    @Test
    void testDeleteExistingUser() {
        // Arrange
        User user = new User();
        user.setCollateralIdNo(1L);
        user.setCreditLimit(10.0d);
        user.setLoanStatus(LoanStatus.APPROVED);
        user.setId("42");
        user.setIdNo(1L);
        user.setIncome(10.0d);
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setSmsId("42");
        user.setSurname("Doe");

        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(userRepository).deleteById(any());

        // Act and Assert
        userServiceImpl.deleteUser("42");
        verify(userRepository).findById(any());
        verify(userRepository).deleteById(any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(String)}
     */
    @Test
    void testDeleteNotExistingUser() {
        // Arrange
        when(userRepository.findById(any())).thenThrow(new UserNotFoundException("User not found"));

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUser("42"));
        verify(userRepository, never()).deleteById(any());
    }
}

