package com.management.library.configuration;

import com.management.library.entity.User;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationAuditAware implements AuditorAware<User> {
    @NonNull
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        PrincipalUser principal = (PrincipalUser) authentication.getPrincipal();
        User user = User.builder()
                .id(principal.getId())
                .username(principal.getUsername())
                .password(principal.getPassword())
                .build();
        return Optional.ofNullable(user);
    }
}