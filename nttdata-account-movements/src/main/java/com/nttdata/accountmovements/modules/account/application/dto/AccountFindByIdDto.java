package com.nttdata.accountmovements.modules.account.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Clase DTO para representar la informacion de una cuenta al buscar por ID.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountFindByIdDto {

    private Integer cnumerocuenta;
    private Integer ccliente;
    private String tipocuenta;
    private BigDecimal saldoinicial;
    private boolean estado;

}
