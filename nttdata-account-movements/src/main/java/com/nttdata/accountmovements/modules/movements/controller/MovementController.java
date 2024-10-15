package com.nttdata.accountmovements.modules.movements.controller;

import com.nttdata.accountmovements.modules.common.ApiResponse;
import com.nttdata.accountmovements.modules.common.Frame;
import com.nttdata.accountmovements.modules.movements.application.MovementApplication;
import com.nttdata.accountmovements.modules.movements.domain.MovementDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Controlador REST para gestionar los movimientos de cuenta.
 */
@RestController
@RequestMapping("/movimientos")
public class MovementController {

    private final MovementApplication movementApplication;

    /**
     * Constructor para inyectar la dependencia de MovementApplication.
     *
     * @param movementApplication instancia de MovementApplication
     */
    public MovementController(MovementApplication movementApplication) {
        this.movementApplication = movementApplication;
    }

    /**
     * Inserta un nuevo movimiento en la base de datos.
     *
     * @param movementDomain objeto MovementDomain que contiene los datos del movimiento
     * @return ResponseEntity con el estado de la operacion
     */
    @PostMapping()
    public ResponseEntity<Frame> insert(@RequestBody MovementDomain movementDomain) {
        this.movementApplication.insert(movementDomain);
        return ApiResponse.ok();
    }

    /**
     * Obtiene un reporte de movimientos entre dos fechas.
     *
     * @param fechaInicio fecha de inicio en formato String (yyyy-MM-dd)
     * @param fechaFin    fecha de fin en formato String (yyyy-MM-dd)
     * @return ResponseEntity con el reporte de movimientos
     */
    @GetMapping
    public ResponseEntity<Frame> getReportePorFechas(@RequestParam String fechaInicio, @RequestParam String fechaFin) {
        return ApiResponse.ok(this.movementApplication.findByFechaBetween(Date.valueOf(fechaInicio),
                Date.valueOf(fechaFin)));
    }

    /**
     * Obtiene los movimientos de una cuenta especifica.
     *
     * @param cnumerocuenta numero de cuenta
     * @return ResponseEntity con los movimientos de la cuenta
     */
    @GetMapping("/{cnumerocuenta}")
    public ResponseEntity<Frame> getMovimientosPorCuenta(@PathVariable Integer cnumerocuenta) {
        return ApiResponse.ok(this.movementApplication.findByCnumerocuenta(cnumerocuenta));
    }

}
