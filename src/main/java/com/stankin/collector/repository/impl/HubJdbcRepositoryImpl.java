package com.stankin.collector.repository.impl;

import com.stankin.collector.domain.table.Hub;
import com.stankin.collector.repository.HubJdbcRepository;
import com.stankin.collector.repository.HubRepository;
import javassist.NotFoundException;
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
    private final HubRepository hubRepository;

    private final String INSERT_HUB = "INSERT INTO " +
            "public.hubs(name, location, description, v_ver, created_at, updated_at, device_list_available) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final String SELECT_HUB = "SELECT name, location, description, v_ver, created_at, " +
            "updated_at, device_list_available::text, id\n" +
            "\tFROM public.hubs WHERE id = ?";

    @Autowired
    public HubJdbcRepositoryImpl(JdbcTemplate jdbcTemplate,
                                 HubRepository hubRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.hubRepository = hubRepository;
    }

    @Override
    public Hub save(Hub hub, String json) throws SQLException {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PGobject jsonbObj = new PGobject();
        jsonbObj.setType("json");
        jsonbObj.setValue("{\"key\" : \"value\"}");

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_HUB, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, hub.getName());
            ps.setString(2, hub.getLocation());
            ps.setString(3, hub.getDescription());
            ps.setString(4, hub.getVVer());
            ps.setDate(5, new Date(hub.getCreatedAt().getTime()));
            ps.setDate(6, new Date(hub.getUpdatedAt().getTime()));
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
        return Optional.of(newHub);
    }


    @Override
    public void update() {

    }
}
