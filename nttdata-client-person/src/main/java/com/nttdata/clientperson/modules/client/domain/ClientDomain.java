package com.nttdata.clientperson.modules.client.domain;

import com.nttdata.clientperson.modules.common.ApiException;
import com.nttdata.clientperson.modules.person.domain.PersonDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el dominio del cliente.
 * Extiende de PersonDomain.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDomain extends PersonDomain {

    private Integer ccliente;
    private String contrasenia;
    private boolean estado;

    /**
     * Metodo para validar los campos del cliente.
     * Lanza una ApiException si algun campo requerido es nulo o vacio.
     */
    public void validate() {
        if (ccliente == null || contrasenia == null || contrasenia.isEmpty() ||
                getNombre() == null || getNombre().isEmpty() || getGenero() == null || getGenero().isEmpty() ||
                getEdad() == null || getIdentificacion() == null || getIdentificacion().isEmpty() ||
                getDireccion() == null || getDireccion().isEmpty() || getTelefono() == null || getTelefono().isEmpty()) {
            throw new ApiException("All fields are required");
        }
    }

}
