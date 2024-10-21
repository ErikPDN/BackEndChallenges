package br.erik.challengs.btgpactual.orderms.controller.dto;

import br.erik.challengs.btgpactual.orderms.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderId,
                            Long costumerId,
                            BigDecimal total) {

    public static OrderResponse fromEntity(OrderEntity order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerId(),
                order.getTotal()
        );
    }
}
