package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.messaging.kafka.OrderMessagingService;
import sia.tacocloud.models.TacoOrder;
import sia.tacocloud.models.User;
import sia.tacocloud.repos.OrderRepository;
import sia.tacocloud.web.OrderProps;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;

    private OrderProps orderProps;

    private OrderMessagingService messagingService;

    public OrderController(OrderRepository orderRepository, OrderProps orderProps, OrderMessagingService messagingService) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
        this.messagingService = messagingService;
    }

    @GetMapping
    public String ordersForUsers(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute("tacoOrder") TacoOrder tacoOrder) {
        if (tacoOrder.getDeliveryName() == null) {
            tacoOrder.setDeliveryName(user.getFullname());
        }
        if (tacoOrder.getDeliveryStreet() == null) {
            tacoOrder.setDeliveryStreet(user.getStreet());
        }
        if (tacoOrder.getDeliveryCity() == null) {
            tacoOrder.setDeliveryCity(user.getCity());
        }
        if (tacoOrder.getDeliveryState() == null) {
            tacoOrder.setDeliveryState(user.getState());
        }
        if (tacoOrder.getDeliveryZip() == null) {
            tacoOrder.setDeliveryZip(user.getZip());
        }
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "orderForm";
        tacoOrder.setUser(user);
        orderRepository.save(tacoOrder);
        messagingService.sendOrder(tacoOrder);
//        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
