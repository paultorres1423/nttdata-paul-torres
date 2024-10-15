package com.nttdata.accountmovements.modules.movements.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase DTO para representar los movimientos de cuenta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementfindByCnumerocuentaDto {

    private Integer cmovimiento;
    private Integer cnumerocuenta;
    private Date fecha;
    private String tipomovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;

}
