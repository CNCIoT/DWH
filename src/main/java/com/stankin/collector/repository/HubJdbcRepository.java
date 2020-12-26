package com.stankin.collector.repository;

import com.stankin.collector.domain.table.Hub;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.Optional;

public interface HubJdbcRepository {

    Hub save(Hub hub) throws SQLException;
    Optional<Hub> findById(@NotNull Long id);
    void update(@NotNull Hub hub, @NotNull String json);
}
