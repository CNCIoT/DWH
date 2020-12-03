package com.stankin.collector.repository;

import com.stankin.collector.domain.table.Data;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataDTORepository extends Repository<Data, Long> {

    @Query("SELECT id, device_id, value::text, created FROM values WHERE device_id = :device_id\n" +
            "ORDER BY created DESC LIMIT :rows")
    List<Data> findByDeviceIdWithRows(@Param("device_id") Long deviceId, @Param("rows") Long rows);
}
