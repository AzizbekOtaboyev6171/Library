package com.management.library.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error("Username not found: {}", e.getMessage());
        return new ResponseEntity<>("Username not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    public ResponseEntity<String> handleBadCredentialsException(Exception e) {
        log.error("Bad credentials: {}", e.getMessage());
        return new ResponseEntity<>("Bad credentials: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException e) {
        log.error("Token expired: {}", e.getMessage());
        return new ResponseEntity<>("Token expired: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        log.error("Resource already exists: {}", e.getMessage());
        return new ResponseEntity<>("Resource already exists: " + e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("Resource not found: {}", e.getMessage());
        return new ResponseEntity<>("Resource not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<String> handleResourceConflictException(ResourceConflictException e) {
        log.error("Resource conflict: {}", e.getMessage());
        return new ResponseEntity<>("Resource conflict: " + e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        log.error("Access denied: {}", e.getMessage());
        return new ResponseEntity<>("Access denied: " + e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            log.error("Validation error - Field: {}, Message: {}", fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<String> handleBookNotAvailableException(BookNotAvailableException e) {
        log.error("Book not available: {}", e.getMessage());
        return new ResponseEntity<>("Book not available: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoanAlreadyReturnedException.class)
    public ResponseEntity<String> handleLoanAlreadyReturnedException(LoanAlreadyReturnedException e) {
        log.error("Loan already returned: {}", e.getMessage());
        return new ResponseEntity<>("Loan already returned: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}