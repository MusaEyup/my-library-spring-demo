package com.spring.app.business.services;

import com.spring.app.entities.Role;
import com.spring.app.results.DataResult;

import java.util.List;

public interface RoleService {
    DataResult<Role> getRoleByName(String name);
    DataResult<List<Role>> getAllRoles();
}
