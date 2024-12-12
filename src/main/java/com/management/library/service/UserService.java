package com.management.library.service;

import com.management.library.dto.user.UserCreateDTO;
import com.management.library.dto.user.UserDTO;
import com.management.library.dto.user.UserUpdateDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    UserDTO getUserById(Long id);

    void deleteUserById(Long id);

    Page<UserDTO> getAllUsers(int page, int size);
}