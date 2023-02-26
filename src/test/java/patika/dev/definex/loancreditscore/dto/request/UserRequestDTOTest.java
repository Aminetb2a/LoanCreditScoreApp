package patika.dev.definex.loancreditscore.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class UserRequestDTOTest {

    private final static UserRequestDTO userRequestDTO = new UserRequestDTO();
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldValidateIdNoLength() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setIdNo(1435364346473454L);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateIdNoNegative() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setIdNo(-543633144L);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateNameNull() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setName(null);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }


    @Test
    void shouldValidateNameEmpty() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setName("");

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }


    @Test
    void shouldValidateNameSize() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setName("n");

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateIncomeNegative() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setIncome(-543633144.0d);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateSurnameNull() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setSurname(null);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateSurnameEmpty() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setSurname("");

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateSurnameSize() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setSurname("s");

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateSurnameBigSize() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setSurname("sihfnkew fwsd fwqedsa fweasd");

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateBirthDateNull() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setBirthDate(null);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateBirthDateFuture() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        LocalDateTime date = LocalDate.parse("3999-12-31").atStartOfDay();
        userRequestDTO.setBirthDate(Date.from(date.atZone(ZoneId.of("UTC")).toInstant()));
        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidatePhoneNumber() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setPhoneNumber("5662550144");

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidatePhoneNumberEmpty() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setPhoneNumber("");

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidatePhoneNumberNull() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setPhoneNumber(null);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateCollateralIdNoNegative() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setCollateralIdNo(-97243532354L);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateCollateralIdNoSize() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setCollateralIdNo(2423534435254535L);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldValidateCollateralIdNoNull() {
        // Arrange
        UserRequestDTO userRequestDTO = getUserRequestDTO();
        userRequestDTO.setPhoneNumber(null);

        // Act and Assert
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertFalse(violations.isEmpty());
    }


    private UserRequestDTO getUserRequestDTO() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userRequestDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userRequestDTO.setCollateralIdNo(198657575L);
        userRequestDTO.setId("42");
        userRequestDTO.setIdNo(1936549792345L);
        userRequestDTO.setIncome(10.0d);
        userRequestDTO.setName("Name");
        userRequestDTO.setPhoneNumber("+905662555014");
        userRequestDTO.setSurname("Doe");
        return userRequestDTO;
    }

}

