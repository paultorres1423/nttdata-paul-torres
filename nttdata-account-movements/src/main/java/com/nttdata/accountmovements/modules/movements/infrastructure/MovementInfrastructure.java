package com.nttdata.accountmovements.modules.movements.infrastructure;

import com.nttdata.accountmovements.modules.movements.domain.MovementDomain;
import com.nttdata.accountmovements.modules.movements.domain.MovementService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion de la interfaz MovementService que proporciona
 * metodos para interactuar con el repositorio de movimientos.
 */
public class MovementInfrastructure implements MovementService {

    private final MovementRepository movementRepository;

    /**
     * Constructor que inicializa el repositorio de movimientos.
     *
     * @param movementRepository el repositorio de movimientos
     */
    public MovementInfrastructure(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    /**
     * Inserta un nuevo movimiento en el repositorio.
     *
     * @param movementDomain el dominio del movimiento a insertar
     */
    @Override
    public void insert(MovementDomain movementDomain) {
        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setCnumerocuenta(movementDomain.getCnumerocuenta());
        movementEntity.setFecha(movementDomain.getFecha());
        movementEntity.setTipomovimiento(movementDomain.getTipomovimiento());
        movementEntity.setValor(movementDomain.getValor());
        movementEntity.setSaldo(movementDomain.getSaldo());
        movementRepository.save(movementEntity);
    }

    /**
     * Encuentra movimientos en un rango de fechas.
     *
     * @param startDate la fecha de inicio del rango
     * @param endDate   la fecha de fin del rango
     * @return una lista de dominios de movimientos en el rango de fechas
     */
    @Override
    public List<MovementDomain> findByFechaBetween(Date startDate, Date endDate) {
        return movementRepository.findByFechaBetween(startDate, endDate).stream()
                .map(movementEntity -> new MovementDomain(
                        movementEntity.getCmovimiento(),
                        movementEntity.getCnumerocuenta(),
                        movementEntity.getFecha(),
                        movementEntity.getTipomovimiento(),
                        movementEntity.getValor(),
                        movementEntity.getSaldo()
                )).collect(Collectors.toList());
    }

    /**
     * Encuentra movimientos por numero de cuenta.
     *
     * @param cnumerocuenta el numero de cuenta
     * @return una lista de dominios de movimientos para el numero de cuenta
     */
    @Override
    public List<MovementDomain> findByCnumerocuenta(Integer cnumerocuenta) {
        return movementRepository.findByCnumerocuenta(cnumerocuenta).stream()
                .map(movementEntity -> new MovementDomain(
                        movementEntity.getCmovimiento(),
                        movementEntity.getCnumerocuenta(),
                        movementEntity.getFecha(),
                        movementEntity.getTipomovimiento(),
                        movementEntity.getValor(),
                        movementEntity.getSaldo()
                )).collect(Collectors.toList());
    }

    /**
     * Obtiene el saldo de una cuenta.
     *
     * @param cnumerocuenta el numero de cuenta
     * @return el saldo de la cuenta
     */
    @Override
    public BigDecimal getBalance(Integer cnumerocuenta) {
        return movementRepository.getBalance(cnumerocuenta);
    }

}
