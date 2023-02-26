package patika.dev.definex.loancreditscore.service.creditscore.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.enums.IncomeCategory;
import patika.dev.definex.loancreditscore.enums.LoanStatus;
import patika.dev.definex.loancreditscore.service.collateral.CollateralService;
import patika.dev.definex.loancreditscore.service.common.IncomeRangeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CreditLimitCalculatorImpl.class})
@ExtendWith(SpringExtension.class)
class CreditLimitCalculatorImplTest {
    @MockBean
    private CollateralService collateralService;

    @Autowired
    private CreditLimitCalculatorImpl creditLimitCalculatorImpl;

    @MockBean
    private IncomeRangeService incomeRangeService;

    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitLow() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(null);
        when(incomeRangeService.getIncomeCategory(any())).thenReturn(IncomeCategory.LOW);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(1234.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(null);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(10000.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 510.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(any());
        verify(incomeRangeService).getIncomeCategory(any());
    }


    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitLowWithCollateral() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(10000.0d);
        when(incomeRangeService.getIncomeCategory(any())).thenReturn(IncomeCategory.LOW);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(1000.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(13545264757L);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(11000.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 510.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(any());
        verify(incomeRangeService).getIncomeCategory(any());
    }

    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitMedium() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(null);
        when(incomeRangeService.getIncomeCategory(any())).thenReturn(IncomeCategory.MEDIUM);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(9234.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(null);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(20000.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 510.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(any());
        verify(incomeRangeService).getIncomeCategory(any());
    }


    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitMediumWithCollateral() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(10000.0d);
        when(incomeRangeService.getIncomeCategory(any())).thenReturn(IncomeCategory.MEDIUM);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(5100.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(13545264757L);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(22000.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 510.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(any());
        verify(incomeRangeService).getIncomeCategory(any());
    }

    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitHigh() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(null);
        when(incomeRangeService.getIncomeCategory(any())).thenReturn(IncomeCategory.HIGH);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(15000.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(null);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(30000.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 510.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(any());
        verify(incomeRangeService).getIncomeCategory(any());
    }


    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitHighWithCollateral() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(10000.0d);
        when(incomeRangeService.getIncomeCategory(any())).thenReturn(IncomeCategory.HIGH);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(15000.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(13545264757L);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(32500.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 510.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(any());
        verify(incomeRangeService).getIncomeCategory(any());
    }

    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitHigher() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(null);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(15000.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(null);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(60000.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 1500.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(userDTO.getCollateralIdNo());
    }

    /**
     * Method under test: {@link CreditLimitCalculatorImpl#getCreditLimit(UserDTO, Double)}
     */
    @Test
    void testGetCreditLimitHigherWithCollateral() {
        // Arrange
        when(collateralService.getGuaranteeAmount(any())).thenReturn(10000.0d);

        UserDTO userDTO = new UserDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userDTO.setBirthDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        userDTO.setSmsId("42");
        userDTO.setName("Name");
        userDTO.setSurname("Doe");
        userDTO.setIdNo(123537654L);
        userDTO.setIncome(15000.0d);
        userDTO.setCreditLimit(10.0d);
        userDTO.setId("2864983jh354jn983k");
        userDTO.setPhoneNumber("+906625550144");
        userDTO.setCollateralIdNo(13545264757L);
        userDTO.setLoanStatus(LoanStatus.APPROVED);

        // Act and Assert
        assertEquals(65000.0d, creditLimitCalculatorImpl.getCreditLimit(userDTO, 1500.0d).doubleValue());
        verify(collateralService).getGuaranteeAmount(userDTO.getCollateralIdNo());
    }
}

