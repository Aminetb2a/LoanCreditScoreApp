package patika.dev.definex.loancreditscore.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.enums.CreditStatus;
import patika.dev.definex.loancreditscore.model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@DirtiesContext
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void clean() {
        userRepository.deleteAll(); // TODO disable in prod (use a testing db)
    }

    @Test
    public void findExistingUserByIdNoTest() {
        // given
        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setCollateralIdNo(1L);
        user.setCreditLimit(10.0d);
        user.setCreditStatus(CreditStatus.APPROVED);
        user.setId("42");
        user.setIdNo(1L);
        user.setIncome(10.0d);
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setSmsId("42");
        user.setSurname("Doe");
        userRepository.save(user);
        // when
        Optional<User> userByIdNo = userRepository.findByIdNo(user.getIdNo());

        // then
        assertEquals(userByIdNo.get(), user);
    }

    @Test
    public void findNotExistingUserByIdNoTest() {
        // given
        User user = new User();
        user.setIdNo(11L);

        // when
        Optional<User> userByIdNo = userRepository.findByIdNo(user.getIdNo());

        // then
        assertTrue(userByIdNo.isEmpty());
    }

    @Test
    void findExistingUserByIdNoAndBirthDate() {
        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setCollateralIdNo(1L);
        user.setCreditLimit(10.0d);
        user.setCreditStatus(CreditStatus.APPROVED);
        user.setId("42");
        user.setIdNo(1L);
        user.setIncome(10.0d);
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setSmsId("42");
        user.setSurname("Doe");
        userRepository.save(user);
        // when
        Optional<User> userByIdNoAndBirthDate = userRepository.findByIdNoAndBirthDate(user.getIdNo(), user.getBirthDate());

        // then
        assertEquals(userByIdNoAndBirthDate.get(), user);
    }

    @Test
    void findNotExistingUserByIdNoAndBirthDate() {
        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setCollateralIdNo(1L);

        // when
        Optional<User> userByIdNoAndBirthDate = userRepository.findByIdNoAndBirthDate(user.getIdNo(), user.getBirthDate());

        // then
        assertTrue(userByIdNoAndBirthDate.isEmpty());
    }

}