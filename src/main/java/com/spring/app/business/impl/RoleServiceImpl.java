package com.spring.app.business.impl;

import com.spring.app.dao.RoleRepository;
import com.spring.app.entities.Role;
import com.spring.app.results.DataResult;
import com.spring.app.results.ErrorDataResult;
import com.spring.app.results.SuccessDataResult;
import com.spring.app.business.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public DataResult<Role> getRoleByName(String name) {


        Role role = roleRepository.findRoleByName(name);
        if(role != null){
            return new SuccessDataResult<>(role);
        }

        return new ErrorDataResult<>("Role not found");
    }

    @Override
    public DataResult<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        if(roles != null){
            return new SuccessDataResult<>(roles);
        }

        return new ErrorDataResult<>("Role not found");
    }
}
