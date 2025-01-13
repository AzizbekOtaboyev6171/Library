package com.management.library.exceptions;

import com.management.library.dto.exception.*;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error("Username not found: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    public ResponseEntity<ExceptionResponseDTO> handleBadCredentialsException(Exception e) {
        log.error("Bad credentials: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponseDTO> handleJwtException(JwtException e) {
        log.error("Invalid token: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.FORBIDDEN.value(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        log.error("Resource already exists: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.CONFLICT.value(), LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("Resource not found: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceConflictException(ResourceConflictException e) {
        log.error("Resource conflict: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.CONFLICT.value(), LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAccessDeniedException(AccessDeniedException e) {
        log.error("Access denied: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.FORBIDDEN.value(), LocalDateTime.now()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationDTO> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ValidationDTO(fieldName, errorMessage));
        });
        return new ResponseEntity<>(new ValidationExceptionDTO("Validation failed", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleBookNotAvailableException(BookNotAvailableException e) {
        log.error("Book not available: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoanAlreadyReturnedException.class)
    public ResponseEntity<ExceptionResponseDTO> handleLoanAlreadyReturnedException(LoanAlreadyReturnedException e) {
        log.error("Loan already returned: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponseDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("Method not allowed: {}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponseDTO(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value(), LocalDateTime.now()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MissingExceptionDTO> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("Missing request parameter: {}", e.getMessage());
        List<MissingParameterDTO> errors = new ArrayList<>();
        errors.add(new MissingParameterDTO(e.getParameterName(), e.getMessage()));
        return new ResponseEntity<>(new MissingExceptionDTO("Missing request parameter", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), errors), HttpStatus.BAD_REQUEST);
    }
}