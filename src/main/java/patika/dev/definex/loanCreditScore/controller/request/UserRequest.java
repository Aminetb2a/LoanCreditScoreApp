package patika.dev.definex.loanCreditScore.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    // TODO add validation
    private String id;
    private Long idNo;
    private String name;
    private Double income;
    private String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    private String phoneNumber;
    private Long collateralIdNo;
}
