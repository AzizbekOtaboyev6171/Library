package com.management.library.mapper;

import com.management.library.dto.role.RoleDTO;
import com.management.library.entity.Role;
import com.management.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedBy")
    RoleDTO toDTO(Role role);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "roleMapIdToUser")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy", qualifiedByName = "roleMapIdToUser")
    Role toEntity(RoleDTO roleDTO);

    @Named("roleMapIdToUser")
    default User mapIdToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}