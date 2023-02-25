package patika.dev.definex.loancreditscore.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import patika.dev.definex.loancreditscore.enums.CreditStatus;
import patika.dev.definex.loancreditscore.model.BaseModel;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("user")
@CompoundIndexes({@CompoundIndex(def = "{'idNo': 1, 'collateralIdNo': 1}", unique = true), @CompoundIndex(def = "{'phoneNumber': 1 , 'collateralIdNo': 1}", unique = true)})
public class User extends BaseModel {
    private Long idNo;
    private String name;
    private String smsId;
    private Double income;
    private String surname;
    private Date birthDate;
    private Double creditLimit;
    private String phoneNumber;
    private Long collateralIdNo;
    private CreditStatus creditStatus;
}
