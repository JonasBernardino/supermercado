package com.jb.supermercado.config.exception.handler;

import com.jb.supermercado.config.exception.RecursoNaoEncontradoException;
import com.jb.supermercado.config.exception.response.ErroPadraoResponse;
import com.jb.supermercado.config.exception.response.ErroValidacaoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        ErroValidacaoResponse response = new ErroValidacaoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos",
                erros
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadraoResponse> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {

        ErroPadraoResponse response = new ErroPadraoResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}