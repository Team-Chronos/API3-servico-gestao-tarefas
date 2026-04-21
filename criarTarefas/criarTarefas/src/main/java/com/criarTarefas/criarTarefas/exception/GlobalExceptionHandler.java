package com.criarTarefas.criarTarefas.exception;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.criarTarefas.criarTarefas.modelo.DTO.erroLimiteHorasProjetoDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(limiteHorasProjetoException.class)
    public ResponseEntity<erroLimiteHorasProjetoDTO> handleLimiteHorasProjeto(limiteHorasProjetoException ex) {
        erroLimiteHorasProjetoDTO body = new erroLimiteHorasProjetoDTO(
                OffsetDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                "Limite de horas excedido",
                ex.getMessage(),
                ex.getProjetoId(),
                ex.getMinutosDisponiveis(),
                ex.getTempoDisponivelFormatado(),
                ex.getTempoUsadoFormatado(),
                ex.getLimiteProjetoFormatado()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", OffsetDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Dados inválidos");

        Map<String, String> fields = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fields.put(error.getField(), error.getDefaultMessage())
        );

        body.put("fields", fields);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", OffsetDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Erro interno");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}