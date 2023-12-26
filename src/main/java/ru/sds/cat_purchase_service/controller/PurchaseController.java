package ru.sds.cat_purchase_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sds.cat_purchase_service.model.dto.PurchaseDBParamsDto;
import ru.sds.cat_purchase_service.model.dto.PurchaseDto;
import ru.sds.cat_purchase_service.model.dto.PurchaseInfoRequestDto;
import ru.sds.cat_purchase_service.service.PurchaseService;

@Tag(name = "Purchase")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "Create purchase")
    @PostMapping
    public Long create(@RequestBody PurchaseDto purchaseDto) {
        return purchaseService.createPurchase(purchaseDto);
    }

    @Operation(summary = "Get purchase info")
    @GetMapping
    public PurchaseDBParamsDto getInfo(PurchaseInfoRequestDto purchaseInfoRequestDto) {
        return purchaseService.getInfo(purchaseInfoRequestDto.getSale_id());
    }
}
