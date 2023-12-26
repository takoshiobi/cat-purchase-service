package ru.sds.cat_purchase_service.rabbitmq;

public interface PurchaseProducer {

    void purchase(Long catId, Long purchaseId);
}
