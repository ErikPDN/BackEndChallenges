package br.erik.challengs.btgpactual.orderms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import br.erik.challengs.btgpactual.orderms.listener.dto.OrderCreatedEvent;
import br.erik.challengs.btgpactual.orderms.service.OrderService;

import static br.erik.challengs.btgpactual.orderms.config.RabbitMQConfig.ORDER_CREATED_QUEUE;


@Component
public class OrderCreatedListener {
    
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        logger.info("Message consumed: {}", message);

        orderService.save(message.getPayload());
    }
}
