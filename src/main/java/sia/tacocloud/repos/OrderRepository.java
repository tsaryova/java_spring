package sia.tacocloud.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.models.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}
