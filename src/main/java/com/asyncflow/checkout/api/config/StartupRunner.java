package com.asyncflow.checkout.api.config;

import com.asyncflow.checkout.api.domain.entity.Order;
import com.asyncflow.checkout.api.domain.enums.OrderStatus;
import com.asyncflow.checkout.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

    private final RabbitAdmin rabbitAdmin;
    private final Queue queue;
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) {
        log.info("Iniciando smoke test de infraestrutura...");

        rabbitAdmin.declareQueue(queue);
        log.info("Fila criada com sucesso!");

        Order order = Order.builder()
                .productCode("SMOKE_TEST_PRODUCT")
                .quantity(1)
                .totalPrice(BigDecimal.ONE)
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);
        log.info("Pedido de teste salvo com ID: {}", savedOrder.getId());

        log.info("--- AMBIENTE DE DESENVOLVIMENTO INICIADO ---");
    }
}

