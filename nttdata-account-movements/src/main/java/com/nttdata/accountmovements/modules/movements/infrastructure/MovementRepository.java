package com.nttdata.accountmovements.modules.movements.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Repositorio para la entidad MovementEntity.
 * Proporciona metodos para realizar operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {

    /**
     * Encuentra movimientos entre dos fechas especificas.
     *
     * @param startDate Fecha de inicio.
     * @param endDate   Fecha de fin.
     * @return Lista de movimientos entre las fechas especificadas.
     */
    List<MovementEntity> findByFechaBetween(Date startDate, Date endDate);


    /**
     * Encuentra movimientos por numero de cuenta.
     *
     * @param cnumerocuenta Numero de cuenta.
     * @return Lista de movimientos para el numero de cuenta especificado.
     */
    List<MovementEntity> findByCnumerocuenta(Integer cnumerocuenta);

    /**
     * Encuentra movimientos por numero de cuenta.
     *
     * @param cnumerocuenta Numero de cuenta.
     * @return Lista de movimientos para el numero de cuenta especificado.
     */
    @Query("select bal.saldo from MovementEntity bal where bal.cmovimiento = (select max(tmov.cmovimiento) from " +
            "MovementEntity tmov)")
    BigDecimal getBalance(@Param("cnumerocuenta") Integer cnumerocuenta);

}
