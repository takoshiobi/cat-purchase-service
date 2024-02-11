package ru.sds.cat_purchase_service.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sds.cat_purchase_service.mapper.PurchaseDBParamsMapper;
import ru.sds.cat_purchase_service.model.dto.PurchaseDBParamsDto;

import java.sql.Timestamp;
import java.util.List;

@Repository
@AllArgsConstructor
public class PurchaseRepository {

    private static final String SELECT_FIND_BY_ID = """
            SELECT *
            FROM straycats.purchase
            WHERE id = ?
            """;

    private static final String INSERT_CREATE_PURCHASE = """
            INSERT INTO straycats.purchase
            (create_ts, status)
            VALUES (?, ?)
            RETURNING *;
            """;

    private static final String UPDATE_UPDATE_PURCHASE = """
            UPDATE straycats.purchase
            SET update_ts = ?, status = ?, description = ?
            WHERE id = ?
            RETURNING *;
            """;

    private final PurchaseDBParamsMapper purchaseDBParamsMapper;
    private final JdbcTemplate jdbcTemplate;

    public List<PurchaseDBParamsDto> findById(final Long id) {
        return jdbcTemplate.query(SELECT_FIND_BY_ID, purchaseDBParamsMapper, id);
    }

    public PurchaseDBParamsDto create(final Timestamp createTs,
                                      final String status
    ) {
        return jdbcTemplate.queryForObject(INSERT_CREATE_PURCHASE, purchaseDBParamsMapper,
                createTs, status);
    }

    public PurchaseDBParamsDto update(final Long id,
                                      final Timestamp updateTs,
                                      final String status,
                                      final String description) {
        return jdbcTemplate.queryForObject(UPDATE_UPDATE_PURCHASE, purchaseDBParamsMapper,
                updateTs, status, description, id);
    }
}
