package sia.tacocloud.messaging.rabbit.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.models.TacoOrder;

@Controller
@RequestMapping("/ordersrabbit")
@RequiredArgsConstructor
public class OrderRecieverController {

    private final OrderReciever orderReciever;

    @GetMapping("/receive")
    public String recieveOrder(Model model) {
        TacoOrder order = orderReciever.receiveOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "receiveOrder";
        }
        return "noOrder";
    }

}
