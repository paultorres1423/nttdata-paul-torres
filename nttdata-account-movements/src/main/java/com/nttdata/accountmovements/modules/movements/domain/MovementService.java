package com.nttdata.accountmovements.modules.movements.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Interface para el servicio de movimientos.
 */
public interface MovementService {

    void insert(MovementDomain movementDomain);

    List<MovementDomain> findByFechaBetween(Date startDate, Date endDate);

    List<MovementDomain> findByCnumerocuenta(Integer cnumerocuenta);

    BigDecimal getBalance(Integer cnumerocuenta);

}
