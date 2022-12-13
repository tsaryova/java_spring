package sia.tacocloud.messaging.rabbit.receiver.listener;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sia.tacocloud.messaging.rabbit.receiver.KitchenUI;
import sia.tacocloud.models.TacoOrder;

@Component
@Slf4j
public class OrderListener {

//    private KitchenUI ui;
//
//    @Autowired
//    public OrderListener(KitchenUI ui) {
//        this.ui = ui;
//    }

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(Object obj) {
        log.info("RECEIVED ORDER:  " + obj);
    }

}
