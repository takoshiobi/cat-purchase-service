package ru.sds.cat_purchase_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PurchaseDto {

    @Schema(description = "Purchased cat id")
    @NonNull
    @JsonProperty("cat_id")
    private Long catId;
}
