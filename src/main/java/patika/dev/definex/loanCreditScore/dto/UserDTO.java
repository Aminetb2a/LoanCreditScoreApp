package patika.dev.definex.loanCreditScore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String id;
    private Long idNo;
    private String name;
    private Double income;
    private String surname;
    private Date birthDate;
    private String phoneNumber;
    private Long collateralIdNo;
}
