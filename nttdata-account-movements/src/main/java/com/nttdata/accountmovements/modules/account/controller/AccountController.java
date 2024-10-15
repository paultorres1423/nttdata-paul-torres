package com.nttdata.accountmovements.modules.account.controller;

import com.nttdata.accountmovements.modules.account.application.AccountApplication;
import com.nttdata.accountmovements.modules.account.domain.AccountDomain;
import com.nttdata.accountmovements.modules.common.ApiResponse;
import com.nttdata.accountmovements.modules.common.Frame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar las cuentas.
 */
@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private final AccountApplication accountApplication;

    /**
     * Constructor para inyectar la dependencia de AccountApplication.
     *
     * @param accountApplication la aplicacion de cuentas
     */
    public AccountController(AccountApplication accountApplication) {
        this.accountApplication = accountApplication;
    }

    /**
     * Inserta una nueva cuenta.
     *
     * @param accountDomain el dominio de la cuenta a insertar
     * @return una respuesta HTTP con el estado de la operacion
     */
    @PostMapping()
    public ResponseEntity<Frame> insert(@RequestBody AccountDomain accountDomain) {
        this.accountApplication.insert(accountDomain);
        return ApiResponse.ok();
    }

    /**
     * Actualiza una cuenta existente.
     *
     * @param accountDomain el dominio de la cuenta a actualizar
     * @return una respuesta HTTP con el estado de la operacion
     */
    @PutMapping()
    public ResponseEntity<Frame> update(@RequestBody AccountDomain accountDomain) {
        this.accountApplication.update(accountDomain);
        return ApiResponse.ok();
    }


    /**
     * Elimina una cuenta por su ID.
     *
     * @param id el ID de la cuenta a eliminar
     * @return una respuesta HTTP con el estado de la operacion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Frame> delete(@PathVariable Integer id) {
        this.accountApplication.delete(id);
        return ApiResponse.ok();
    }

    /**
     * Busca una cuenta por su ID.
     *
     * @param id el ID de la cuenta a buscar
     * @return una respuesta HTTP con la cuenta encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Frame> findById(@PathVariable Integer id) {
        return ApiResponse.ok(this.accountApplication.findById(id));
    }

}
