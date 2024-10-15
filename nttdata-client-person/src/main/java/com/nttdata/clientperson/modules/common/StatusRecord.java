package com.nttdata.clientperson.modules.common;

/**
 * Enumeracion que representa el estado de un registro.
 */
public enum StatusRecord {

    ACTIVE(true),
    INACTIVE(false);

    private final boolean value;

    /**
     * Constructor para inicializar el estado del registro.
     *
     * @param value Valor booleano del estado.
     */
    StatusRecord(boolean value) {
        this.value = value;
    }

    /**
     * Obtiene el valor booleano del estado del registro.
     *
     * @return Valor booleano del estado.
     */
    public boolean getValue() {
        return value;
    }

}
