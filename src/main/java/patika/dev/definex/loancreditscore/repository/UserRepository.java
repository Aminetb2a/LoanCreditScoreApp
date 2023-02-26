package patika.dev.definex.loancreditscore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import patika.dev.definex.loancreditscore.model.user.User;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Method that query Users by using the ID number.
     *
     * @param idNo user's ID number
     * @return User
     */
    Optional<User> findByIdNo(Long idNo);

    /**
     * Method that query Users by the ID number and birth date.
     *
     * @param idNo user's ID number
     * @return User
     */
    Optional<User> findByIdNoAndBirthDate(Long idNo, Date birthDate);
}
