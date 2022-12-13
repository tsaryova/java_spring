package sia.tacocloud.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    private final RabbitTemplate rabbitTemplate;
//
//    public HomeController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    @GetMapping("/")
    public String home() {
        //rabbitTemplate.convertAndSend("tacocloud.order.queue","TestAngelina");
        return "home";
    }
}
