package patika.dev.definex.loanCreditScore.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    // TODO add validation
    private Long idNo;
    private String name;
    private Double income;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String phoneNumber;
    private Long collateralIdNo;
}
