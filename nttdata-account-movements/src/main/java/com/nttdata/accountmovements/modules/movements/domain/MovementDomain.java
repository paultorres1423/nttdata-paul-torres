package com.nttdata.accountmovements.modules.movements.domain;

import com.nttdata.accountmovements.modules.common.ApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase que representa un movimiento en una cuenta bancaria.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementDomain {

    private Integer cmovimiento;
    private Integer cnumerocuenta;
    private Date fecha;
    private String tipomovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;

    /**
     * Valida los campos obligatorios del movimiento.
     *
     * @throws ApiException si algun campo obligatorio esta ausente.
     */
    public void validate() {
        if (this.cnumerocuenta == null) throw new ApiException("Number account is required");
        if (this.fecha == null) throw new ApiException("Date is required");
        if (this.tipomovimiento == null) throw new ApiException("Type movement is required");
        if (this.valor == null) throw new ApiException("Value is required");
    }

}
