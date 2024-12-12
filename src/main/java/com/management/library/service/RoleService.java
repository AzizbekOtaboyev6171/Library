package com.management.library.service;

import com.management.library.dto.role.RoleCreateDTO;
import com.management.library.dto.role.RoleDTO;
import com.management.library.dto.role.RoleUpdateDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleCreateDTO roleCreateDTO);

    RoleDTO updateRole(Long id, RoleUpdateDTO roleUpdateDTO);

    RoleDTO getRoleById(Long id);

    void deleteRoleById(Long id);

    List<RoleDTO> getAllRoles();
}