package sia.tacocloud.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.models.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
