package patika.dev.definex.loanCreditScore.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import patika.dev.definex.loanCreditScore.controller.request.UserRequest;
import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.service.impl.UserServiceImpl;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loan")
public class LoanCreditScoreController {
    private final UserServiceImpl userService;

    @Operation(summary = "Create a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @PostMapping("user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(new UserDTOMapper().mapToModel(userRequest));
    }

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PutMapping("user/update")
    public UserDTO updateUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(new UserDTOMapper().mapToModel(userRequest));
    }
}
