package patika.dev.definex.loancreditscore.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableMongoRepositories("patika.dev.definex.loancreditscore.repository")
@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class MongoConfig {
    /**
     * This method returns the current date and time as a LocalDateTime object.
     * used for the @LastModifiedDate and @CreatedDate annotations used in the
     * BaseModel, to keep track of the records insertion and updates
     *
     * @return Optional.of(LocalDateTime.now ())
     */
    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now());
    }
}
