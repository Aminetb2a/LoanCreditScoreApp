package patika.dev.definex.loanCreditScore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("user")
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    @Id
    private String id;
    private Long idNo;
    private String name;
    private Double income;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String phoneNumber;
    private Long collateralIdNo;
}
