package patika.dev.definex.loancreditscore.service.creditscore.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.enums.LoanStatus;
import patika.dev.definex.loancreditscore.service.creditscore.CreditLimitCalculatorService;
import patika.dev.definex.loancreditscore.service.creditscore.LoanStatusService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CreditScoreServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CreditScoreServiceImplTest {
    @MockBean
    private CreditLimitCalculatorService creditLimitCalculatorService;

    @Autowired
    private CreditScoreServiceImpl creditScoreServiceImpl;

    @MockBean
    private LoanStatusService creditStatusService;

    /**
     * Method under test: {@link CreditScoreServiceImpl#processCreditScore(UserDTO)}
     */
    @Test
    void testProcessCreditScore() {
        // Arrange
        when(creditStatusService.getLoanStatus(any())).thenReturn(LoanStatus.APPROVED);
        when(creditLimitCalculatorService.getCreditLimit(any(), any())).thenReturn(10.0d);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(10234.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(13545264757L);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act
        CreditScore actualProcessCreditScoreResult = creditScoreServiceImpl.processCreditScore(userDTO);

        // Assert
        assertEquals(10.0d, actualProcessCreditScoreResult.getLimit().doubleValue());
        assertEquals(LoanStatus.APPROVED, actualProcessCreditScoreResult.getStatus());
        verify(creditStatusService).getLoanStatus(any());
        verify(creditLimitCalculatorService).getCreditLimit(any(), any());
    }

    /**
     * Method under test: {@link CreditScoreServiceImpl#processCreditScore(UserDTO)}
     */
    @Test
    void testProcessCreditScore2() {
        // Arrange
        when(creditStatusService.getLoanStatus(any())).thenReturn(LoanStatus.REJECTED);
        when(creditLimitCalculatorService.getCreditLimit(any(), any())).thenReturn(0.0d);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(10234.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(13545264757L);
        userDTO.setLoanStatus(LoanStatus.REJECTED);

        // Act
        CreditScore actualProcessCreditScoreResult = creditScoreServiceImpl.processCreditScore(userDTO);

        // Assert
        assertEquals(0.0d, actualProcessCreditScoreResult.getLimit().doubleValue());
        assertEquals(LoanStatus.REJECTED, actualProcessCreditScoreResult.getStatus());
        verify(creditStatusService).getLoanStatus(any());
    }
}

