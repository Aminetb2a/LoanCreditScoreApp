package patika.dev.definex.loancreditscore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import patika.dev.definex.loancreditscore.model.user.User;

public interface UserRepository extends MongoRepository<User, String> {
}
