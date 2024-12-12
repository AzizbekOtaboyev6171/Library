package com.management.library.service.impl;

import com.management.library.dto.user.UserCreateDTO;
import com.management.library.dto.user.UserDTO;
import com.management.library.dto.user.UserUpdateDTO;
import com.management.library.entity.User;
import com.management.library.exceptions.ResourceAlreadyExistsException;
import com.management.library.exceptions.ResourceNotFoundException;
import com.management.library.mapper.UserMapper;
import com.management.library.repository.UserRepository;
import com.management.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        log.info("Creating user with username: {}", userCreateDTO.username());
        if (userRepository.existsByUsername(userCreateDTO.username())) {
            throw new ResourceAlreadyExistsException("User with username " + userCreateDTO.username() + " already exists");
        }

//        Set<Role> roles = userCreateDTO.roles().stream()
//                .map(role -> roleRepository.findById(role)
//                        .orElseThrow(() -> new ResourceNotFoundException("Role not found")))
//                .collect(Collectors.toSet());

        User user = new User();
        user.setUsername(userCreateDTO.username());
        user.setPassword(passwordEncoder.encode(userCreateDTO.password()));
//        user.setRoles(roles);
        User saved = userRepository.save(user);
        log.info("User with id: {} created successfully!", saved.getId());
        return userMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        log.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        if (userRepository.existsByUsernameAndIdNot(userUpdateDTO.username(), id)) {
            throw new ResourceAlreadyExistsException("User with username " + userUpdateDTO.username() + " already exists");
        }

//        Set<Role> roles = userUpdateDTO.roles().stream()
//                .map(role -> roleRepository.findById(role)
//                        .orElseThrow(() -> new ResourceNotFoundException("Role not found")))
//                .collect(Collectors.toSet());

        user.setUsername(userUpdateDTO.username());
        user.setPassword(passwordEncoder.encode(userUpdateDTO.password()));
        user.setWorkTime(userUpdateDTO.workTime());
//        user.setRoles(roles);
        User saved = userRepository.save(user);
        log.info("User with id: {} updated successfully!", id);
        return userMapper.toDTO(saved);
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Getting user with id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.deleteById(id);
        log.info("User with id: {} deleted successfully!", id);
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size) {
        log.info("Getting all users");
        return userRepository.findAll(PageRequest.of(page, size))
                .map(userMapper::toDTO);
    }
}