package com.nttdata.clientperson.modules.client.infrastructure;

import com.nttdata.clientperson.modules.person.infrastructure.PersonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un cliente en la base de datos.
 * Hereda de PersonEntity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientEntity extends PersonEntity {

    @Column(name = "ccliente", unique = true)
    private Integer ccliente;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "estado")
    private boolean estado;

}
