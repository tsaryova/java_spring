package sia.tacocloud.messaging.rabbit.receiver;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import sia.tacocloud.models.TacoOrder;

@Component("templateOrderReceiver")
public class RabbitOrderReceiver implements OrderReciever{

    private RabbitTemplate rabbit;

    public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public TacoOrder receiveOrder() {
        return (TacoOrder) rabbit.receiveAndConvert("tacocloud.order.queue");
    }
}
