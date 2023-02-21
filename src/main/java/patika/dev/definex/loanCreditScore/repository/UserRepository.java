package patika.dev.definex.loanCreditScore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import patika.dev.definex.loanCreditScore.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}
