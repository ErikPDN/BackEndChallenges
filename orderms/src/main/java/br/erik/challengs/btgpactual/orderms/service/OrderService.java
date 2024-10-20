package br.erik.challengs.btgpactual.orderms.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.erik.challengs.btgpactual.orderms.entity.OrderEntity;
import br.erik.challengs.btgpactual.orderms.listener.dto.OrderCreatedEvent;
import br.erik.challengs.btgpactual.orderms.repository.OrderRepository;
import br.erik.challengs.btgpactual.orderms.entity.OrderItem;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {
        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustumerId(event.codigoCliente());
        entity.setItens(getOrderItems(event));
        entity.setTotal(getTotal(event));
        
        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

    private List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco()))
                .toList();
    }
}
