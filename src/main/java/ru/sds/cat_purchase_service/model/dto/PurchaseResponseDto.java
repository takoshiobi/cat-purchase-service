package ru.sds.cat_purchase_service.model.dto;

import lombok.Data;

@Data
public class PurchaseResponseDto {

    private Long purchaseId;
    private String status;
    private String description;
}
