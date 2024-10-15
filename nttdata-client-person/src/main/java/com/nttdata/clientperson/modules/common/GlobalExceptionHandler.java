package com.nttdata.clientperson.modules.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase GlobalExceptionHandler que maneja excepciones globales en la aplicacion.
 * Utiliza @ControllerAdvice para aplicar consejos a todos los controladores.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo RuntimeException.
     *
     * @param e la excepcion RuntimeException que fue lanzada.
     * @return un ResponseEntity con la respuesta de error.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Frame> handleRuntimeException(RuntimeException e) {
        log.error("Error", e);
        return ApiResponse.error(e);
    }

}
