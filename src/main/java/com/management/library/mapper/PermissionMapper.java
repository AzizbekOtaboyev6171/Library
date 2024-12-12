package com.management.library.mapper;

import com.management.library.dto.permission.PermissionDTO;
import com.management.library.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {
}