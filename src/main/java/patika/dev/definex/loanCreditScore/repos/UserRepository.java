package patika.dev.definex.loanCreditScore.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import patika.dev.definex.loanCreditScore.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
}
