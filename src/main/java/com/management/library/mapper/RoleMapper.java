package com.management.library.mapper;

import com.management.library.dto.role.RoleDTO;
import com.management.library.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}