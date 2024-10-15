package com.nttdata.clientperson.modules.client.application;

import com.nttdata.clientperson.modules.client.domain.ClientDomain;
import com.nttdata.clientperson.modules.client.domain.ClientService;
import com.nttdata.clientperson.modules.common.ApiException;
import com.nttdata.clientperson.modules.common.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase que representa la aplicacion del cliente.
 */
public class ClientApplication {

    private static final String CLIENT_NOT_FOUND = "Client not found";
    private final ClientService clientService;

    @Autowired
    private RabbitMQSender rabbitMQSender;


    /**
     * Constructor de la clase ClientApplication.
     *
     * @param clientService  Servicio de cliente.
     * @param rabbitMQSender Enviador de mensajes RabbitMQ.
     */
    public ClientApplication(ClientService clientService, RabbitMQSender rabbitMQSender) {
        this.clientService = clientService;
        this.rabbitMQSender = rabbitMQSender;
    }

    /**
     * Inserta un nuevo cliente.
     *
     * @param clientDomain Dominio del cliente a insertar.
     */
    public void insert(ClientDomain clientDomain) {
        clientDomain.validate();
        this.clientService.insert(clientDomain);
        this.rabbitMQSender.send(clientDomain.getCcliente().toString());
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param clientDomain Dominio del cliente a actualizar.
     * @throws ApiException Si el cliente no es encontrado.
     */
    public void update(ClientDomain clientDomain) {
        ClientDomain clientDomainUpdate = this.clientService.findById(clientDomain.getCpersona());
        if (clientDomainUpdate == null) {
            throw new ApiException(CLIENT_NOT_FOUND);
        }
        clientDomainUpdate.setNombre(clientDomain.getNombre());
        clientDomainUpdate.setIdentificacion(clientDomain.getIdentificacion());
        clientDomainUpdate.setDireccion(clientDomain.getDireccion());
        clientDomainUpdate.setTelefono(clientDomain.getTelefono());
        clientDomainUpdate.setContrasenia(clientDomain.getContrasenia());
        clientDomainUpdate.setGenero(clientDomain.getGenero());
        clientDomainUpdate.setEdad(clientDomain.getEdad());
        clientDomainUpdate.validate();
        this.clientService.update(clientDomainUpdate);
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id ID del cliente a eliminar.
     * @throws ApiException Si el cliente no es encontrado.
     */
    public void delete(Integer id) {
        ClientDomain clientDomain = this.clientService.findById(id);
        if (clientDomain == null) {
            throw new ApiException(CLIENT_NOT_FOUND);
        }
        this.clientService.delete(id);
    }

    /**
     * Encuentra un cliente por su ID.
     *
     * @param id ID del cliente a encontrar.
     * @return DTO con la informacion del cliente encontrado.
     * @throws ApiException Si el cliente no es encontrado.
     */
    public ClientFindByIdDto findById(Integer id) {
        ClientDomain clientDomain = this.clientService.findById(id);
        if (clientDomain == null) {
            throw new ApiException(CLIENT_NOT_FOUND);
        }
        return new ClientFindByIdDto(
                clientDomain.getCcliente(),
                clientDomain.getContrasenia(),
                clientDomain.isEstado(),
                clientDomain.getCpersona(),
                clientDomain.getNombre(),
                clientDomain.getGenero(),
                clientDomain.getEdad(),
                clientDomain.getIdentificacion(),
                clientDomain.getDireccion(),
                clientDomain.getTelefono()
        );
    }

}
