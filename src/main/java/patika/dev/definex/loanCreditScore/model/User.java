package patika.dev.definex.loanCreditScore.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("user")
@NoArgsConstructor
@Accessors(chain = true)
public class User extends BaseModel {
    private Long idNo;
    private String name;
    private Double income;
    private String surname;
    private Date birthDate;
    private String phoneNumber;
    private Long collateralIdNo;
}
