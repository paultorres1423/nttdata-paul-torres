package com.nttdata.clientperson.modules.client.domain;

/**
 * Interface para el servicio de cliente.
 * Proporciona metodos para insertar, actualizar, eliminar y buscar clientes.
 */
public interface ClientService {

    void insert(ClientDomain clientDomain);

    void update(ClientDomain clientDomain);

    void delete(Integer id);

    ClientDomain findById(Integer id);

}
