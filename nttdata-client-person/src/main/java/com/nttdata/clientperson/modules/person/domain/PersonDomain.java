package com.nttdata.clientperson.modules.person.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el dominio de una persona.
 * Utiliza Lombok para generar automaticamente los metodos getter, setter,
 * constructor sin argumentos y constructor con todos los argumentos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDomain {

    private Integer cpersona;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

}
