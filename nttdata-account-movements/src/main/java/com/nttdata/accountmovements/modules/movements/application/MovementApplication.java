package com.nttdata.accountmovements.modules.movements.application;

import com.nttdata.accountmovements.modules.common.ApiException;
import com.nttdata.accountmovements.modules.movements.application.dto.MovementfindByCnumerocuentaDto;
import com.nttdata.accountmovements.modules.movements.application.dto.MovementfindByFechaBetweenDto;
import com.nttdata.accountmovements.modules.movements.domain.MovementDomain;
import com.nttdata.accountmovements.modules.movements.domain.MovementService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Clase MovementApplication que maneja la logica de negocio para los movimientos de cuenta.
 */
public class MovementApplication {

    private final MovementService movementService;

    /**
     * Constructor de MovementApplication.
     *
     * @param movementService Servicio de movimientos que maneja las operaciones de la base de datos.
     */
    public MovementApplication(MovementService movementService) {
        this.movementService = movementService;
    }

    /**
     * Inserta un nuevo movimiento en la cuenta.
     *
     * @param movementDomain Objeto MovementDomain que contiene los datos del movimiento.
     * @throws ApiException Si el saldo resultante es negativo.
     */
    public void insert(MovementDomain movementDomain) {
        movementDomain.validate();
        BigDecimal balance = calculateNewBalance(movementDomain);
        if (balance.compareTo(BigDecimal.ZERO) < 0) throw new ApiException("Saldo no disponible");
        movementDomain.setSaldo(balance);
        this.movementService.insert(movementDomain);
    }

    /**
     * Calcula el nuevo saldo despues de un movimiento.
     *
     * @param movementDomain Objeto MovementDomain que contiene los datos del movimiento.
     * @return BigDecimal Nuevo saldo despues del movimiento.
     */
    private BigDecimal calculateNewBalance(MovementDomain movementDomain) {
        BigDecimal balance = this.getBalance(movementDomain.getCnumerocuenta());
        balance = (balance == null) ? BigDecimal.ZERO : balance;
        return "D".equals(movementDomain.getTipomovimiento()) ? balance.subtract(movementDomain.getValor()) :
                balance.add(movementDomain.getValor());
    }

    /**
     * Encuentra movimientos entre dos fechas.
     *
     * @param startDate Fecha de inicio.
     * @param endDate   Fecha de fin.
     * @return List<MovementfindByFechaBetweenDto> Lista de movimientos entre las fechas especificadas.
     */
    public List<MovementfindByFechaBetweenDto> findByFechaBetween(Date startDate, Date endDate) {
        List<MovementDomain> listMovementDomain = this.movementService.findByFechaBetween(startDate, endDate);
        return listMovementDomain.stream().map(movementDomain -> new MovementfindByFechaBetweenDto(
                movementDomain.getCmovimiento(),
                movementDomain.getCnumerocuenta(),
                movementDomain.getFecha(),
                movementDomain.getTipomovimiento(),
                movementDomain.getValor(),
                movementDomain.getSaldo()
        )).collect(java.util.stream.Collectors.toList());
    }

    /**
     * Encuentra movimientos por numero de cuenta.
     *
     * @param cnumerocuenta Numero de cuenta.
     * @return List<MovementfindByCnumerocuentaDto> Lista de movimientos para el numero de cuenta especificado.
     */
    public List<MovementfindByCnumerocuentaDto> findByCnumerocuenta(Integer cnumerocuenta) {
        List<MovementDomain> listMovementDomain = this.movementService.findByCnumerocuenta(cnumerocuenta);
        return listMovementDomain.stream().map(movementDomain -> new MovementfindByCnumerocuentaDto(
                movementDomain.getCmovimiento(),
                movementDomain.getCnumerocuenta(),
                movementDomain.getFecha(),
                movementDomain.getTipomovimiento(),
                movementDomain.getValor(),
                movementDomain.getSaldo()
        )).collect(java.util.stream.Collectors.toList());
    }

    /**
     * Obtiene el saldo de una cuenta.
     *
     * @param cnumerocuenta Numero de cuenta.
     * @return BigDecimal Saldo de la cuenta.
     */
    public BigDecimal getBalance(Integer cnumerocuenta) {
        return this.movementService.getBalance(cnumerocuenta);
    }

}
