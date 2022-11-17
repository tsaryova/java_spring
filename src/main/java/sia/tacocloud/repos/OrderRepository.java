package sia.tacocloud.repos;

import sia.tacocloud.models.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
