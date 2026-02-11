package com.asyncflow.checkout.api.service;

import com.asyncflow.checkout.api.config.RabbitMQConfig;
import com.asyncflow.checkout.api.domain.dto.OrderRequest;
import com.asyncflow.checkout.api.domain.dto.OrderResponse;
import com.asyncflow.checkout.api.domain.entity.Order;
import com.asyncflow.checkout.api.domain.enums.OrderStatus;
import com.asyncflow.checkout.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .productCode(request.productCode())
                .quantity(request.quantity())
                .totalPrice(request.price().multiply(BigDecimal.valueOf(request.quantity())))
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, savedOrder.getId().toString());

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getStatus().name(),
                "Pedido recebido e aguardando processamento"
        );
    }
}
