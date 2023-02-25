package patika.dev.definex.loancreditscore.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioProperties {
    private String username;
    private String password;
    private String phoneNumber;
}
