package com.asyncflow.checkout.api.consumer;

import com.asyncflow.checkout.api.config.RabbitMQConfig;
import com.asyncflow.checkout.api.domain.enums.OrderStatus;
import com.asyncflow.checkout.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processOrder(String payload) {
        log.info("Worker recebeu o payload: {}", payload);

        try {
            UUID orderId = UUID.fromString(payload);

            orderRepository.findById(orderId).ifPresent(order -> {
                order.setStatus(OrderStatus.APPROVED);
                orderRepository.save(order);
                log.info("Pedido {} aprovado e atualizado no banco!", orderId);
            });

        } catch (IllegalArgumentException e) {
            log.error("Payload inválido recebido na fila: {}", payload);
        }

        // Simulação de delay / gerar uma nota fiscal/chamar gateway de pagamento - RECOMENDO REMOVER
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Interrupção: {}", e.getMessage());
        }
    }
}