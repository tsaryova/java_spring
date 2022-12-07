package sia.tacocloud.messaging.rabbit;

import sia.tacocloud.models.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder order);

}
