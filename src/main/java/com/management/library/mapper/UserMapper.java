package com.management.library.mapper;

import com.management.library.dto.user.UserDTO;
import com.management.library.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {
}