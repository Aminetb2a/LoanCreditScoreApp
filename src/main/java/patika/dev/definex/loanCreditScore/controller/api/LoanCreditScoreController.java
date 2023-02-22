package patika.dev.definex.loanCreditScore.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import patika.dev.definex.loanCreditScore.controller.request.UserRequest;
import patika.dev.definex.loanCreditScore.dto.mapper.UserDTOMapper;
import patika.dev.definex.loanCreditScore.service.UserService;

@RestController
@RequiredArgsConstructor
public class LoanCreditScoreController {
    private final UserService userService;

    @PostMapping("user/add")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok( userService.createUser(new UserDTOMapper().mapToModel(userRequest)));
    }
}
