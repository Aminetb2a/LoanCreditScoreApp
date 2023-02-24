package patika.dev.definex.loanCreditScore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.util.Date;

@Data
@NoArgsConstructor
public class BaseModel {
    @Id
    private String id;
    @Version
    private Long version;
    @LastModifiedDate
    private Date updateTime;
    @CreatedDate
    private Date insertTime;
}
