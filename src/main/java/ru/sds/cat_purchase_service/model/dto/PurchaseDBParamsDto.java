package ru.sds.cat_purchase_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class PurchaseDBParamsDto {

    private Long id;

    private Timestamp createTs;

    private Timestamp updateTs;

    private String status;

    private String description;
}
