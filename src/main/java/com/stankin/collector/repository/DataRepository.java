package com.stankin.collector.repository;

import com.stankin.collector.domain.document.Data;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import java.util.Date;

public interface DataRepository extends Repository<Data, Long> {

    @Modifying
    @Query("INSERT INTO values(device_id, value, created) VALUES (:device_id, :value::jsonb, :created)")
    void save(@Param("device_id") Long deviceId, @Param("value") String value,
              @Param("created") Date created);
}
