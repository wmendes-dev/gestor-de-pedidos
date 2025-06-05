package br.com.autenticacao_api.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    protected ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        return new ResponseEntity<>(
                ApplicationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .details(List.of(ex.getMessage()))
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<>(
                ApplicationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .title(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        .details(List.of("Credenciais inválidas"))
                        .developerMessage(ex.getClass().getName())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", "Token expirado");
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("details", List.of(ex.getMessage()));
        body.put("developerMessage", ex.getClass().getName());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> fieldValidationList = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        return new ResponseEntity<>(
                ApplicationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .details(fieldValidationList)
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAnyOtherException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Erro interno no servidor", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        LOGGER.error("Exceção não tratada pela aplicação", ex);
        return new ResponseEntity<>(
                ApplicationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(statusCode.value())
                        .title((String) body)
                        .details(List.of(ex.getMessage()))
                        .developerMessage(ex.getClass().getName())
                        .build(), statusCode);
    }

}