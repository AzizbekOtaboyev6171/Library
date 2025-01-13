package com.management.library.mapper;

import com.management.library.dto.user.UserDTO;
import com.management.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedBy")
    UserDTO toDTO(User user);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userMapIdToUser")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy", qualifiedByName = "userMapIdToUser")
    User toEntity(UserDTO userDTO);

    @Named("userMapIdToUser")
    default User mapIdToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}