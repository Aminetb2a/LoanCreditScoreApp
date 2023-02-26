package patika.dev.definex.loancreditscore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import patika.dev.definex.loancreditscore.config.exception.UserFoundException;
import patika.dev.definex.loancreditscore.config.exception.UserNotFoundException;
import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.request.UserRequestDTO;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.enums.LoanStatus;
import patika.dev.definex.loancreditscore.service.user.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = LoanCreditScoreController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class LoanCreditScoreControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysExpect(forwardedUrl(null))
                .build();
    }

    /**
     * Method under test: {@link LoanCreditScoreController#applyToLoan(UserRequestDTO)}
     */
    @Test
    void shouldReturnSuccessfulApplyToLoan() throws Exception {
        // Arrange
        CreditScore creditScore = CreditScore.builder()
                .limit(10.0d)
                .status(LoanStatus.APPROVED)
                .build();
        when(userService.applyToLoan(any())).thenReturn(creditScore);

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userRequestDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userRequestDTO.setCollateralIdNo(1L);
        userRequestDTO.setIdNo(1L);
        userRequestDTO.setIncome(10.0d);
        userRequestDTO.setName("Name");
        userRequestDTO.setPhoneNumber("+905662550144");
        userRequestDTO.setSurname("Doe");

        // Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/loan/user/apply")
                        .content(objectMapper.writeValueAsString(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.limit", is(creditScore.getLimit())))
                .andExpect(jsonPath("$.status", is(creditScore.getStatus().toString())));
        verify(userService).applyToLoan(any());
    }

    /**
     * Method under test: {@link LoanCreditScoreController#applyToLoan(UserRequestDTO)}
     */
    @Test
    void shouldNotAddDuplicateUser() throws Exception {
        // Arrange
        when(userService.applyToLoan(any())).thenThrow(new UserFoundException());

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userRequestDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userRequestDTO.setCollateralIdNo(1L);
        userRequestDTO.setIdNo(1L);
        userRequestDTO.setIncome(10.0d);
        userRequestDTO.setName("Name");
        userRequestDTO.setPhoneNumber("+905662550144");
        userRequestDTO.setSurname("Doe");

        // Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/loan/user/apply")
                        .content(objectMapper.writeValueAsString(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(userService).applyToLoan(any());
    }

    /**
     * Method under test: {@link LoanCreditScoreController#applyToLoan(UserRequestDTO)}
     */
    @Test
    void testApplyToLoanWithEmptyPayload() throws Exception {
        // Arrange & Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/loan/user/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Method under test: {@link LoanCreditScoreController#updateUser(UserRequestDTO)}
     */
    @Test
    void testUpdateUserWithEmptyPayload() throws Exception {
        // Arrange & Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/loan/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Method under test: {@link LoanCreditScoreController#updateUser(UserRequestDTO)}
     */
    @Test
    void shouldUpdateUser() throws Exception {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userRequestDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userRequestDTO.setCollateralIdNo(1L);
        userRequestDTO.setIdNo(193753424L);
        userRequestDTO.setIncome(10.0d);
        userRequestDTO.setName("Name");
        userRequestDTO.setPhoneNumber("+905662550144");
        userRequestDTO.setSurname("Doe");

        UserDTO userDTO = new UserDTO();
        userDTO.setBirthDate(userRequestDTO.getBirthDate());
        userDTO.setCollateralIdNo(userRequestDTO.getCollateralIdNo());
        userDTO.setIdNo(userRequestDTO.getIdNo());
        userDTO.setIncome(userRequestDTO.getIncome());
        userDTO.setName(userRequestDTO.getName());
        userDTO.setPhoneNumber(userRequestDTO.getPhoneNumber());
        userDTO.setSurname(userRequestDTO.getSurname());
        when(userService.updateUser(any())).thenReturn(userDTO);

        // & Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/loan/user/update")
                        .content(objectMapper.writeValueAsBytes(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(userDTO.getName())))
                .andExpect(jsonPath("$.idNo", is(userDTO.getIdNo().intValue())))
                .andExpect(jsonPath("$.phoneNumber", is(userDTO.getPhoneNumber())))
                .andExpect(jsonPath("$.birthDate", is(birthDate.toLocalDate().toString())));
        verify(userService).updateUser(any());
    }

    /**
     * Method under test: {@link LoanCreditScoreController#updateUser(UserRequestDTO)}
     */
    @Test
    void shouldNotUpdateNotExistingUser() throws Exception {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        userRequestDTO.setBirthDate(Date.from(birthDate.atZone(ZoneId.of("UTC")).toInstant()));
        userRequestDTO.setCollateralIdNo(1L);
        userRequestDTO.setIdNo(193753424L);
        userRequestDTO.setIncome(10.0d);
        userRequestDTO.setName("Name");
        userRequestDTO.setPhoneNumber("+905662550144");
        userRequestDTO.setSurname("Doe");

        when(userService.updateUser(any())).thenThrow(new UserNotFoundException("User Not Found"));

        // & Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/loan/user/update")
                        .content(objectMapper.writeValueAsBytes(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(userService).updateUser(any());
    }

    /**
     * Method under test: {@link LoanCreditScoreController#deleteUser/{id}}
     */
    @Test
    void shouldDeleteUser() throws Exception {
        // Arrange & Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/loan/user/delete/1y8rg30h453r9253")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link LoanCreditScoreController#getCreditScoreReport?{idNo}&{birthDate})}
     */
    @Test
    void shouldReturnLoanReport() throws Exception {
        // Arrange
        CreditScore creditScore = CreditScore.builder()
                .limit(10.0d)
                .status(LoanStatus.APPROVED)
                .build();
        when(userService.getCreditScoreReport(any(), any())).thenReturn(creditScore);

        // & Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/loan/report")
                        .param("idNo", "86345208024524")
                        .param("birthdate", "1998-03-12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", is(creditScore.getLimit())))
                .andExpect(jsonPath("$.status", is(creditScore.getStatus().toString())));
        verify(userService).getCreditScoreReport(any(), any());

    }

    /**
     * Method under test: {@link LoanCreditScoreController#getCreditScoreReport?{idNo}&{birthDate})}
     */
    @Test
    void shouldNotReturnLoanReportWhenUserNotFound() throws Exception {
        // Arrange
        when(userService.getCreditScoreReport(any(), any())).thenThrow(new UserNotFoundException("User Not Found"));

        // & Act & Assert
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/loan/report")
                        .param("idNo", "86345208024524")
                        .param("birthdate", "1998-03-12")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(userService).getCreditScoreReport(any(), any());

    }

}
