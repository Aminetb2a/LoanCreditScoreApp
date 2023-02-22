package patika.dev.definex.loanCreditScore.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import patika.dev.definex.loanCreditScore.controller.request.UserRequest;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.model.BaseResponse;
import patika.dev.definex.loanCreditScore.service.UserService;

@Validated
@RestController
@RequiredArgsConstructor
public class LoanCreditScoreController {
    private final UserService userService;

    @PostMapping("user/add")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest){
        BaseResponse response = userService.createUser(new UserDTOMapper().mapToModel(userRequest));
        return ResponseEntity.status(response.getSuccess()
                ? HttpStatus.OK.value()
                :HttpStatus.BAD_REQUEST.value())
                .body(response);
    }

    @PutMapping("user/update")
    public ResponseEntity updateUser(@RequestBody UserRequest userRequest){
        BaseResponse response = userService.updateUser(new UserDTOMapper().mapToModel(userRequest));
        return ResponseEntity.status(response.getSuccess()
                        ? HttpStatus.OK.value()
                        :HttpStatus.BAD_REQUEST.value())
                .body(response);
    }
}
