package patika.dev.definex.loancreditscore.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserRequestDTO {
    private String id;
    @Positive
    @Digits(integer = 15, fraction = 0)
    private Long idNo;
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @Positive
    private Double income;
    @NotBlank
    @Size(min = 2, max = 20)
    private String surname;
    @Past
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    @NotBlank
    @Pattern(regexp = "^\\+\\d{12}$", message = "The phone number must have 12 characters of format +xxxyyyy...")
    private String phoneNumber;
    @Digits(integer = 15, fraction = 0)
    @Positive
    private Long collateralIdNo;
}
