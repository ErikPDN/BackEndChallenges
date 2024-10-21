package br.erik.challengs.btgpactual.orderms.repository;

import br.erik.challengs.btgpactual.orderms.controller.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.erik.challengs.btgpactual.orderms.entity.OrderEntity;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity,Long> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
