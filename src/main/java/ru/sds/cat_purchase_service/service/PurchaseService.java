package ru.sds.cat_purchase_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sds.cat_purchase_service.model.dto.PurchaseDBParamsDto;
import ru.sds.cat_purchase_service.model.dto.PurchaseDto;
import ru.sds.cat_purchase_service.model.dto.PurchaseResponseDto;
import ru.sds.cat_purchase_service.rabbitmq.PurchaseProducer;
import ru.sds.cat_purchase_service.repository.PurchaseRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProducer purchaseProducer;

    private enum Status {
        RECEIVED,
        PROCESSED
    }

    public PurchaseDBParamsDto getInfo(Long id) {
        List<PurchaseDBParamsDto> purchase = purchaseRepository.findById(id);
        if (purchase.isEmpty()) {
            throw new RuntimeException("Can't find purchase by id");
        }
        return purchase.getFirst();
    }

    @Transactional
    public Long createPurchase(PurchaseDto purchaseDto) {
        PurchaseDBParamsDto purchase = purchaseRepository.create(
                new Timestamp(System.currentTimeMillis()),
                Status.RECEIVED.toString());
        purchaseProducer.purchase(
                purchaseDto.getCat_id(),
                purchase.getId());
        purchaseRepository.update(
                purchase.getId(),
                new Timestamp(System.currentTimeMillis()),
                Status.PROCESSED.toString(),
                String.format("Processing purchase of cat with id %s", purchaseDto.getCat_id())
        );
        return purchase.getId();
    }

    @RabbitListener(queues = "purchase-cat-response-mq")
    @Transactional
    public void updatePurchase(@Payload PurchaseResponseDto purchaseResponseDto) {
        List<PurchaseDBParamsDto> purchase = purchaseRepository.findById(purchaseResponseDto.getPurchaseId());

        if (purchase.isEmpty()) {
            log.warn(String.format("Update failed. Purchase with id %s doesn't exist", purchaseResponseDto.getPurchaseId()));
        }

        purchaseRepository.update(
                purchaseResponseDto.getPurchaseId(),
                new Timestamp(System.currentTimeMillis()),
                purchaseResponseDto.getStatus(),
                purchaseResponseDto.getDescription()
        );
    }
}
