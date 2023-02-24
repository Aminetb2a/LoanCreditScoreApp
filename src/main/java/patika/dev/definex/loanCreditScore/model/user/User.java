package patika.dev.definex.loanCreditScore.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import patika.dev.definex.loanCreditScore.enums.CreditStatus;
import patika.dev.definex.loanCreditScore.model.BaseModel;

import java.util.Date;

@Data
@Document("user")
@NoArgsConstructor
@Accessors(chain = true)
@CompoundIndex(def = "{'idNo': 1, 'phoneNumber': 1, 'collateralIdNo': 1}", unique = true)
public class User extends BaseModel {
    private Long idNo;
    private String name;
    private Double income;
    private String surname;
    private Date birthDate;
    private Double creditLimit;
    private String phoneNumber;
    private Long collateralIdNo;
    private CreditStatus creditStatus;
}
