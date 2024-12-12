package com.management.library.controller;

import com.management.library.dto.role.RoleCreateDTO;
import com.management.library.dto.role.RoleDTO;
import com.management.library.dto.role.RoleUpdateDTO;
import com.management.library.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    @PostMapping("/create_role")
    @Operation(summary = "Create a new role", tags = {"Role"})
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleCreateDTO roleCreateDTO) {
        return ResponseEntity.ok(roleService.createRole(roleCreateDTO));
    }

    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @PutMapping("/update_role/{id}")
    @Operation(summary = "Update a role", tags = {"Role"})
    public ResponseEntity<RoleDTO> updateRole(@PathVariable(name = "id") Long id, @Valid @RequestBody RoleUpdateDTO roleUpdateDTO) {
        return ResponseEntity.ok(roleService.updateRole(id, roleUpdateDTO));
    }

    @PreAuthorize("hasAuthority('VIEW_ROLES')")
    @GetMapping("/get_role/{id}")
    @Operation(summary = "Get a role", tags = {"Role"})
    public ResponseEntity<RoleDTO> getRole(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/delete_role/{id}")
    @Operation(summary = "Delete a role", tags = {"Role"})
    public ResponseEntity<String> deleteRole(@PathVariable(name = "id") Long id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('VIEW_ROLES')")
    @GetMapping("/get_roles")
    @Operation(summary = "Get all roles", tags = {"Role"})
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}