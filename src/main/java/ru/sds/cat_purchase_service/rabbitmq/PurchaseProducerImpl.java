package ru.sds.cat_purchase_service.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "rabbitmq.main.enable", havingValue = "true")
@AllArgsConstructor
public class PurchaseProducerImpl implements PurchaseProducer {

    private final AmqpTemplate template;

    public void purchase(Long catId, Long purchaseId) {
        PurchaseRequest request = PurchaseRequest.builder()
                .catId(catId)
                .purchaseId(purchaseId)
                .build();
        template.convertAndSend("purchase-cat-request-mq", request);
    }

    @Data
    @Builder
    public static class PurchaseRequest {
        private Long catId;
        private Long purchaseId;
    }
}
