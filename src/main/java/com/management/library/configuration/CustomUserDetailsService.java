package com.management.library.configuration;

import com.management.library.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: {}", username);
        return userRepository.findByUsername(username)
                .map(user -> new PrincipalUser(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword()
//                        user.getRoles()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}