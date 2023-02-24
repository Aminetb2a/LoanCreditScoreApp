package patika.dev.definex.loanCreditScore.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import patika.dev.definex.loanCreditScore.enums.CreditStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String id;
    private Long idNo;
    private String name;
    private Double income;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String phoneNumber;
    private Double creditLimit;
    private Long collateralIdNo;
    private CreditStatus creditStatus;
}
