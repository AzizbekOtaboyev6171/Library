package com.management.library.configuration;

import com.management.library.entity.Permission;
import com.management.library.entity.Role;
import com.management.library.entity.User;
import com.management.library.repository.PermissionRepository;
import com.management.library.repository.RoleRepository;
import com.management.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Initializing data...");

        Permission createRole = permissionRepository.findByNameEqualsIgnoreCase("CREATE_ROLE")
                .orElseGet(() -> {
                    log.info("Creating CREATE_ROLE permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("CREATE_ROLE")
                                    .description("Permission to create role")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Permission viewRoles = permissionRepository.findByNameEqualsIgnoreCase("VIEW_ROLES")
                .orElseGet(() -> {
                    log.info("Creating VIEW_ROLES permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("VIEW_ROLES")
                                    .description("Permission to view roles")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Permission editRole = permissionRepository.findByNameEqualsIgnoreCase("EDIT_ROLE")
                .orElseGet(() -> {
                    log.info("Creating EDIT_ROLE permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("EDIT_ROLE")
                                    .description("Permission to edit role")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Permission deleteRole = permissionRepository.findByNameEqualsIgnoreCase("DELETE_ROLE")
                .orElseGet(() -> {
                    log.info("Creating DELETE_ROLE permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("DELETE_ROLE")
                                    .description("Permission to delete role")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Permission createUser = permissionRepository.findByNameEqualsIgnoreCase("CREATE_USER")
                .orElseGet(() -> {
                    log.info("Creating CREATE_USER permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("CREATE_USER")
                                    .description("Permission to create user")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Permission viewUsers = permissionRepository.findByNameEqualsIgnoreCase("VIEW_USERS")
                .orElseGet(() -> {
                    log.info("Creating VIEW_USERS permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("VIEW_USERS")
                                    .description("Permission to view users")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Permission editUser = permissionRepository.findByNameEqualsIgnoreCase("EDIT_USER")
                .orElseGet(() -> {
                    log.info("Creating EDIT_USER permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("EDIT_USER")
                                    .description("Permission to edit user")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Permission deleteUser = permissionRepository.findByNameEqualsIgnoreCase("DELETE_USER")
                .orElseGet(() -> {
                    log.info("Creating DELETE_USER permission...");
                    return permissionRepository.save(
                            Permission.builder()
                                    .name("DELETE_USER")
                                    .description("Permission to delete user")
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .build());
                });

        Role adminRole = roleRepository.findByNameEqualsIgnoreCase("ADMIN")
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name("ADMIN")
                                .description("Admin role")
                                .createdBy(User.builder().id(1L).build())
                                .permissions(Set.of(createRole, viewRoles, editRole, deleteRole,
                                        createUser, viewUsers, editUser, deleteUser))
                                .build()
                ));

        User adminUser = userRepository.findByUsername("Azizbek7735$")
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .username("Azizbek7735$")
                                .password(passwordEncoder.encode("12345678"))
//                                .roles(Set.of(adminRole))
                                .workTime(8)
                                .createdBy(User.builder().id(1L).build())
                                .build()
                ));

        User adminUser1 = userRepository.findByUsername("Jony7788$")
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .username("Jony7788$")
                                .password(passwordEncoder.encode("12345678"))
//                                .roles(Set.of(adminRole))
                                .workTime(8)
                                .createdBy(User.builder().id(1L).build())
                                .build()
                ));

        log.info("Data initialization completed successfully.");
    }
}