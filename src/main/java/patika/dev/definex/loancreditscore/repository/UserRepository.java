package patika.dev.definex.loancreditscore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import patika.dev.definex.loancreditscore.model.user.User;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    //    @Query(value = "{'idNo' : ?0,'birthDate' : ?1}")
    Optional<User> findByIdNoAndBirthDate(Long idNo, Date birthDate);

    Optional<User> findByIdNo(Long idNo);
}
