package com.nttdata.clientperson.modules.client.controller;

import com.nttdata.clientperson.modules.client.application.ClientApplication;
import com.nttdata.clientperson.modules.client.domain.ClientDomain;
import com.nttdata.clientperson.modules.common.ApiResponse;
import com.nttdata.clientperson.modules.common.Frame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar las operaciones relacionadas con los clientes.
 */
@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientApplication clientApplication;

    /**
     * Constructor para inyectar la dependencia de ClientApplication.
     *
     * @param clientApplication instancia de ClientApplication
     */
    public ClientController(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    /**
     * Inserta un nuevo cliente.
     *
     * @param clientDomain objeto que representa el cliente a insertar
     * @return ResponseEntity con el estado de la operacion
     */
    @PostMapping()
    public ResponseEntity<Frame> insert(@RequestBody ClientDomain clientDomain) {
        this.clientApplication.insert(clientDomain);
        return ApiResponse.ok();
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param clientDomain objeto que representa el cliente a actualizar
     * @return ResponseEntity con el estado de la operacion
     */
    @PutMapping()
    public ResponseEntity<Frame> update(@RequestBody ClientDomain clientDomain) {
        this.clientApplication.update(clientDomain);
        return ApiResponse.ok();
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id identificador del cliente a eliminar
     * @return ResponseEntity con el estado de la operacion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Frame> delete(@PathVariable Integer id) {
        this.clientApplication.delete(id);
        return ApiResponse.ok();
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id identificador del cliente a buscar
     * @return ResponseEntity con el cliente encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Frame> findById(@PathVariable Integer id) {
        return ApiResponse.ok(this.clientApplication.findById(id));
    }

}
