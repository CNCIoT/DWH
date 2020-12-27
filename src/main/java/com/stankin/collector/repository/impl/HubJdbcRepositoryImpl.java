package com.stankin.collector.repository.impl;

import com.stankin.collector.domain.table.Hub;
import com.stankin.collector.repository.HubJdbcRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class HubJdbcRepositoryImpl implements HubJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_HUB = "INSERT INTO " +
            "public.hubs(name, location, description, v_ver, created_at, updated_at, device_list_available) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final String SELECT_HUB = "SELECT name, location, description, v_ver, created_at, " +
            "updated_at, device_list_available::text, id\n" +
            "\tFROM public.hubs WHERE id = ?";

    private final String UPDATE_HUB = "UPDATE public.hubs\n" +
            "\tSET name=?, location=?, description=?, v_ver=?, created_at=?, updated_at=?, " +
            "device_list_available=?, id=?\n" +
            "\tWHERE id = ?";

    @Autowired
    public HubJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hub save(Hub hub) throws SQLException {
        log.trace(">> save... hub={}", hub);
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        PGobject jsonbObj = mockPGObject(hub.getDeviceListAvailable());
        java.util.Date date = new java.util.Date();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_HUB, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, hub.getName());
            ps.setString(2, hub.getLocation());
            ps.setString(3, hub.getDescription());
            ps.setString(4, hub.getVVer());
            ps.setDate(5, hub.getCreatedAt() != null ?
                    new Date(hub.getCreatedAt().getTime()) : new Date(date.getTime()));
            ps.setDate(6, hub.getUpdatedAt() != null ?
                    new Date(hub.getUpdatedAt().getTime()) : new Date(date.getTime()));
            ps.setObject(7, jsonbObj);
            return ps;
        }, holder);
        Map<String, Object> keysMap = holder.getKeys();
        if (keysMap == null) {
            throw new SQLDataException("Объект не создан");
        }
        Long newId = (Long) keysMap.get("id");
        Optional<Hub> hubOptional = this.findById(newId);
        if (!hubOptional.isPresent()) {
            throw new IllegalArgumentException("Объект не создан или не найден...id=" + newId);
        }
        return hubOptional.get();
    }

    @Override
    public Optional<Hub> findById(@NotNull Long id) {
        log.trace(">> findById... id={}", id);
        PGobject jsonbObj = new PGobject();
        jsonbObj.setType("json");
        Hub newHub = jdbcTemplate.queryForObject(SELECT_HUB, new Object[]{id}, (rs, rowNum) -> {
            Hub hub = new Hub();
            hub.setId(rs.getLong("id"));
            hub.setCreatedAt(rs.getTimestamp("created_at"));
            hub.setUpdatedAt(rs.getTimestamp("updated_at"));
            hub.setLocation(rs.getString("location"));
            hub.setDescription(rs.getString("description"));
            hub.setDescription(rs.getString("name"));
            hub.setVVer(rs.getString("v_ver"));
            hub.setDeviceListAvailable(rs.getString("device_list_available"));
            return hub;
        });
        log.trace("<<update... newHub={}", newHub);
        if (newHub != null) return Optional.of(newHub);
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void updateDeviceListAvailable(Long id, String deviceListAvailable) {
        log.trace(">>updateDeviceListAvailable... id={}, deviceListAvailable={}", id, deviceListAvailable);
        int updated = jdbcTemplate.update("UPDATE hubs SET device_list_available = ? WHERE id = ?",
                mockPGObject(deviceListAvailable), id);
        log.trace("<<updateDeviceListAvailable... updated={}", updated);
    }

    @SneakyThrows
    private PGobject mockPGObject(String json) {
        PGobject pGobject = new PGobject();
        pGobject.setType("json");
        pGobject.setValue(json);
        return pGobject;
    }
}
