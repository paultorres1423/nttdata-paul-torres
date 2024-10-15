package com.nttdata.accountmovements.modules.account.domain;

import com.nttdata.accountmovements.modules.common.ApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Clase que representa el dominio de una cuenta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDomain {

    private Integer cnumerocuenta;
    private Integer ccliente;
    private String tipocuenta;
    private BigDecimal saldoinicial;
    private boolean estado;

    /**
     * Metodo para validar los campos de la cuenta.
     *
     * @throws ApiException si algun campo requerido es nulo.
     */
    public void validate() {
        if (this.cnumerocuenta == null) throw new ApiException("Number account is required");
        if (this.ccliente == null) throw new ApiException("Code client is required");
        if (this.tipocuenta == null) throw new ApiException("Type account is required");
        if (this.saldoinicial == null) throw new ApiException("Initial balance is required");
    }

}
