package sia.tacocloud;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import sia.tacocloud.models.Taco;
import sia.tacocloud.models.TacoOrder;

import java.util.UUID;

@Configuration
public class PersistenceConfig {
    @Bean
    public ApplicationListener<BeforeSaveEvent> idGenerator() {
        return event -> {
            var entity = event.getEntity();
            if (entity instanceof TacoOrder) {
                ((TacoOrder) entity).setId(1L);
            }
            if (entity instanceof Taco) {
                ((Taco) entity).setId(1L);
            }
        };
    }
}
