package com.management.library.configuration;

import com.management.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        log.info("User with username: {} logged in", username);
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username can not be null or blank");
        }
        userRepository.findByUsername(username).ifPresentOrElse(user -> {
            userRepository.updateLastLogin(user.getId(), LocalDateTime.now());
        }, () -> {
            throw new UsernameNotFoundException("User not found with username: " + username);
        });
    }
}