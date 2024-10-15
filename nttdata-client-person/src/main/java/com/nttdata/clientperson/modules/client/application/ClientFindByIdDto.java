package com.nttdata.clientperson.modules.client.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO para encontrar un cliente por su ID.
 * Utiliza Lombok para generar automaticamente los metodos getter, setter,
 * constructor sin argumentos y constructor con todos los argumentos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientFindByIdDto {

    private Integer ccliente;
    private String contrasenia;
    private boolean estado;
    private Integer cpersona;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

}
