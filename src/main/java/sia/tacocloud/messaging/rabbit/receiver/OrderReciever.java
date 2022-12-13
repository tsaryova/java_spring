package sia.tacocloud.messaging.rabbit.receiver;

import sia.tacocloud.models.TacoOrder;

public interface OrderReciever {

    TacoOrder receiveOrder();

}
