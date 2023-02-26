package patika.dev.definex.loancreditscore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import patika.dev.definex.loancreditscore.dto.creditscore.CreditScore;
import patika.dev.definex.loancreditscore.dto.request.UserRequestDTO;
import patika.dev.definex.loancreditscore.dto.user.UserDTO;
import patika.dev.definex.loancreditscore.dto.user.UserDTOMapper;
import patika.dev.definex.loancreditscore.service.user.UserService;

import java.time.LocalDate;

import static patika.dev.definex.loancreditscore.constant.Common.Path.*;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API + SLASH + LOAN)
public class LoanCreditScoreController {
    private static final UserDTOMapper userDTOMapper = new UserDTOMapper();
    private final UserService userService;

    /**
     * The Apply for loan controller gets the user's request as UserRequestDTO object
     * forwards it to the UserService if valid for processing and returns the CreditScore
     *
     * @param userRequest The request body that is sent to the server.
     * @return CreditScore
     */
    @Operation(summary = "Apply for loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Applied for loan successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditScore.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @PostMapping(APPLY)
    @ResponseStatus(HttpStatus.CREATED)
    public CreditScore applyToLoan(@Valid @RequestBody UserRequestDTO userRequest) {
        return userService.applyToLoan(new UserDTOMapper().mapToUserDTO(userRequest));
    }

    /**
     * The User update controller gets the user's request as UserRequestDTO object
     * forwards it to the UserService if valid for updates and returns the Updated
     * data
     *
     * @param userRequest The request body that is sent to the server.
     * @return UserDTO
     */
    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @PutMapping(USER + SLASH + UPDATE)
    public UserDTO updateUser(@Valid @RequestBody UserRequestDTO userRequest) {
        return userService.updateUser(userDTOMapper.mapToUserDTO(userRequest));
    }

    /**
     * The User deletion controller gets the user's id as path variable
     * forwards it to the UserService if valid for deletion
     *
     * @path id The id of the user to delete.
     */
    @Operation(summary = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @DeleteMapping(USER + SLASH + DELETE + SLASH + "{id}")
    public void deleteUser(@Valid @PathVariable @NotBlank(message = "you need to provide the User ID as path variable") @Size(min = 15) String id) {
        userService.deleteUser(id);
    }

    /**
     * The loan application report controller gets the user's ID number and birth date
     * as parameters then forwards it to the UserService if valid for a check,
     * returns the loan application report
     *
     * @param idNo      The client's ID number.
     * @param birthdate The client's birth date.
     * @return CreditScore
     */
    @Operation(summary = "Loan Application Report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credit Loan Report returned successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditScore.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @GetMapping(APPLICATION + SLASH + REPORT)
    public CreditScore getCreditScoreReport(
            @Valid @RequestParam @Positive @NotNull(message = "you need to provide the User ID number as idNo") @Digits(integer = 15, fraction = 0) Long idNo,
            @Valid @RequestParam @Past @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate) {
        return userService.getCreditScoreReport(idNo, birthdate);
    }
}
