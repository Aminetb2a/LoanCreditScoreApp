package patika.dev.definex.loanCreditScore.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String id;
    @Positive
    @Digits(integer = 15, fraction = 0)
    private Long idNo;
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    @Positive
    private Double income;
    @NotBlank
    @Size(min = 2, max = 20)
    private String surname;
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    @NotBlank
    @Pattern(regexp = "^\\+\\d{12}$", message = "The phone number must have 12 characters of format +xxxyyyy...")
    private String phoneNumber;
    @Digits(integer = 15, fraction = 0)
    @Positive
    private Long collateralIdNo;
}
