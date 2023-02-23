package patika.dev.definex.loanCreditScore.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import patika.dev.definex.loanCreditScore.controller.request.UserRequest;
import patika.dev.definex.loanCreditScore.dto.UserDTO;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.service.impl.UserServiceImpl;

import javax.validation.Valid;


@Validated
@RestController
@RequiredArgsConstructor
public class LoanCreditScoreController {
    private final UserServiceImpl userService;

    @PostMapping("user/add")
    public UserDTO addUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(new UserDTOMapper().mapToModel(userRequest));
    }

    @PutMapping("user/update")
    public UserDTO updateUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(new UserDTOMapper().mapToModel(userRequest));
    }
}
