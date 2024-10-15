package com.nttdata.accountmovements.modules.account.infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidad que representa una cuenta en la base de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tcuenta")
public class AccountEntity {

    @Id
    @Column(name = "cnumerocuenta")
    private Integer cnumerocuenta;

    @Column(name = "ccliente")
    private Integer ccliente;

    @Column(name = "tipocuenta")
    private String tipocuenta;

    @Column(name = "saldoinicial")
    private BigDecimal saldoinicial;

    @Column(name = "estado")
    private boolean estado;

}
