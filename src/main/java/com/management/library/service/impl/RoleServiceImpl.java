package com.management.library.service.impl;

import com.management.library.dto.role.RoleCreateDTO;
import com.management.library.dto.role.RoleDTO;
import com.management.library.dto.role.RoleUpdateDTO;
import com.management.library.entity.Permission;
import com.management.library.entity.Role;
import com.management.library.exceptions.ResourceAlreadyExistsException;
import com.management.library.exceptions.ResourceNotFoundException;
import com.management.library.mapper.RoleMapper;
import com.management.library.repository.PermissionRepository;
import com.management.library.repository.RoleRepository;
import com.management.library.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    @Override
    @Transactional
    public RoleDTO createRole(RoleCreateDTO roleCreateDTO) {
        log.info("Creating role with name: {}", roleCreateDTO.name());
        if (roleRepository.existsByNameEqualsIgnoreCase(roleCreateDTO.name())) {
            log.error("Role with name: {} already exists", roleCreateDTO.name());
            throw new ResourceAlreadyExistsException("Role with name " + roleCreateDTO.name() + " already exists");
        }
        Set<Permission> permissions = roleCreateDTO.permissionIds().stream()
                .map(permission -> permissionRepository.findById(permission)
                        .orElseThrow(() -> {
                            log.error("Permission with id: {} not found", permission);
                            return new ResourceNotFoundException("Permission with id " + permission + " not found");
                        }))
                .collect(Collectors.toSet());
        Role role = new Role();
        role.setName(roleCreateDTO.name());
        role.setDescription(roleCreateDTO.description());
        role.setPermissions(permissions);
        Role saved = roleRepository.save(role);
        log.info("Role with name: {} created successfully!", roleCreateDTO.name());
        return roleMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public RoleDTO updateRole(Long id, RoleUpdateDTO roleUpdateDTO) {
        log.info("Updating role with id: {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Role with id: {} not found", id);
                    return new ResourceNotFoundException("Role with id " + id + " not found");
                });
        if (roleRepository.existsByNameEqualsIgnoreCaseAndIdNot(roleUpdateDTO.name(), id)) {
            log.error("Role with name: {} already exists", roleUpdateDTO.name());
            throw new ResourceAlreadyExistsException("Role with name " + roleUpdateDTO.name() + " already exists");
        }
        Set<Permission> permissions = roleUpdateDTO.permissionIds().stream()
                .map(permission -> permissionRepository.findById(permission)
                        .orElseThrow(() -> {
                            log.error("Permission with id: {} not found", permission);
                            return new ResourceNotFoundException("Permission with id " + permission + " not found");
                        }))
                .collect(Collectors.toSet());
        role.setName(roleUpdateDTO.name());
        role.setDescription(roleUpdateDTO.description());
        role.setPermissions(permissions);
        Role saved = roleRepository.save(role);
        log.info("Role with id: {} updated successfully!", id);
        return roleMapper.toDTO(saved);
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        log.info("Getting role with id: {}", id);
        return roleRepository.findById(id)
                .map(roleMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Role with id: {} not found", id);
                    return new ResourceNotFoundException("Role with id " + id + " not found");
                });
    }

    @Override
    public void deleteRoleById(Long id) {
        log.info("Deleting role with id: {}", id);
        roleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Role with id: {} not found", id);
                    return new ResourceNotFoundException("Role with id " + id + " not found");
                });
        roleRepository.deleteById(id);
        log.info("Role with id: {} deleted successfully!", id);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        log.info("Getting all roles");
        List<RoleDTO> roles = roleRepository.findAll().stream()
                .map(roleMapper::toDTO)
                .toList();
        log.info("Getting all roles successfully!");
        return roles;
    }
}