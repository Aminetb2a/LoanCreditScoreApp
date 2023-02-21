package patika.dev.definex.loanCreditScore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
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
