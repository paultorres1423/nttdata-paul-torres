package com.nttdata.accountmovements.modules.common;

/**
 * Enumeracion que representa las posibles respuestas de un mensaje en el sistema.
 */
public enum MessageResponse {

    SUCCESS("Transacción Correcta"),
    ERROR("Problemas al ejecutar la transacción");

    private final String message;

    MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
