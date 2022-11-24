package sia.tacocloud.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
