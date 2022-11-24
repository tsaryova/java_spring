package sia.tacocloud.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.models.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
