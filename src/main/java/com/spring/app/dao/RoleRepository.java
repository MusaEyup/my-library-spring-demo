package com.spring.app.dao;

import com.spring.app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Long> {


    @Query(value = "SELECT R FROM Role R WHERE R.name = :name")
    Role findRoleByName(@Param("name") String name);
}
