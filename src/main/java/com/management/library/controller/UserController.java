package com.management.library.controller;

import com.management.library.dto.user.UserCreateDTO;
import com.management.library.dto.user.UserDTO;
import com.management.library.dto.user.UserUpdateDTO;
import com.management.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    //    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping("/create_user")
    @Operation(summary = "Create a new user", tags = {"User"})
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }

    //    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PutMapping("/update_user/{id}")
    @Operation(summary = "Update a user", tags = {"User"})
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userUpdateDTO));
    }

    //    @PreAuthorize("hasAuthority('VIEW_USERS')")
    @GetMapping("/get_user/{id}")
    @Operation(summary = "Get a user", tags = {"User"})
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    //    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/delete_user/{id}")
    @Operation(summary = "Delete a user", tags = {"User"})
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully!");
    }

    //    @PreAuthorize("hasAuthority('VIEW_USERS')")
    @GetMapping("/get_users")
    @Operation(summary = "Get all users", tags = {"User"})
    public ResponseEntity<Page<UserDTO>> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }
}