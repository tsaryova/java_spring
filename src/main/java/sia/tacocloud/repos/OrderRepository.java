package sia.tacocloud.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.models.TacoOrder;
import sia.tacocloud.models.User;


import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
        List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
