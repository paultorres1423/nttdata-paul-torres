package com.nttdata.accountmovements.modules.common;

/**
 * Excepcion personalizada para la API.
 * Extiende RuntimeException para manejar errores en tiempo de ejecucion.
 */
public class ApiException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message El mensaje de error.
     */
    public ApiException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y una causa.
     *
     * @param message El mensaje de error.
     * @param cause   La causa del error.
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
