package sia.tacocloud.messaging.kafka;

import sia.tacocloud.models.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder order);

}
