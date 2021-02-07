package com.stankin.machine.core.repository;


import com.stankin.machine.core.domain.InnerUser;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<InnerUser, Long> {
    List<InnerUser> findAll();

    @Query("SELECT * FROM mdc.users WHERE email = :email")
    InnerUser findByEmail(@Param("email") String email);
}
