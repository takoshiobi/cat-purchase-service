package ru.sds.cat_purchase_service.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.sds.cat_purchase_service.model.dto.PurchaseDBParamsDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PurchaseDBParamsMapper implements RowMapper<PurchaseDBParamsDto> {

    private static final String FIELD_NAME_ID = "id";
    private static final String FIELD_NAME_CREATE_TS = "create_ts";
    private static final String FIELD_NAME_UPDATE_TS = "update_ts";
    private static final String FIELD_NAME_STATUS = "status";
    private static final String FIELD_NAME_DESCRIPTION = "description";

    @Override
    public PurchaseDBParamsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PurchaseDBParamsDto.builder()
                .id(rs.getLong(FIELD_NAME_ID))
                .createTs(rs.getTimestamp(FIELD_NAME_CREATE_TS))
                .updateTs(rs.getTimestamp(FIELD_NAME_UPDATE_TS))
                .status(rs.getString(FIELD_NAME_STATUS))
                .description(rs.getString(FIELD_NAME_DESCRIPTION))
                .build();
    }
}
