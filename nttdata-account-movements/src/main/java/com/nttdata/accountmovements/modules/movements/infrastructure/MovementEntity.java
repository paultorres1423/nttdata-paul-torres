package com.nttdata.accountmovements.modules.movements.infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Entidad que representa un movimiento en la base de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tmovimientos")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmovimiento")
    private Integer cmovimiento;

    @Column(name = "cnumerocuenta")
    private Integer cnumerocuenta;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "tipomovimiento")
    private String tipomovimiento;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

}
