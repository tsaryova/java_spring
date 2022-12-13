package sia.tacocloud.messaging.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sia.tacocloud.models.TacoOrder;

@Component
@Slf4j
public class OrderListener {

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(TacoOrder order, ConsumerRecord<String, TacoOrder> record) {
        log.info("Received from partition {} with timestamp {}",
                record.partition(), record.timestamp());
    }

}
