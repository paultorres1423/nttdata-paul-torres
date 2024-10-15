package com.nttdata.clientperson.modules.client.infrastructure;

import com.nttdata.clientperson.modules.client.domain.ClientDomain;
import com.nttdata.clientperson.modules.client.domain.ClientService;
import com.nttdata.clientperson.modules.common.StatusRecord;

/**
 * Implementacion de la interfaz ClientService para manejar la infraestructura del cliente.
 */
public class ClientInfrastructure implements ClientService {

    private final ClientRepository clientRepository;

    /**
     * Constructor para inicializar el repositorio de clientes.
     *
     * @param clientRepository el repositorio de clientes
     */
    public ClientInfrastructure(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param clientDomain el dominio del cliente a insertar
     */
    @Override
    public void insert(ClientDomain clientDomain) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setCcliente(clientDomain.getCcliente());
        clientEntity.setNombre(clientDomain.getNombre());
        clientEntity.setIdentificacion(clientDomain.getIdentificacion());
        clientEntity.setDireccion(clientDomain.getDireccion());
        clientEntity.setTelefono(clientDomain.getTelefono());
        clientEntity.setContrasenia(clientDomain.getContrasenia());
        clientEntity.setEstado(StatusRecord.ACTIVE.getValue());
        clientEntity.setGenero(clientDomain.getGenero());
        clientEntity.setEdad(clientDomain.getEdad());
        clientRepository.save(clientEntity);
    }

    /**
     * Actualiza un cliente existente en la base de datos.
     *
     * @param clientDomain el dominio del cliente a actualizar
     */
    @Override
    public void update(ClientDomain clientDomain) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setCpersona(clientDomain.getCpersona());
        clientEntity.setCcliente(clientDomain.getCcliente());
        clientEntity.setNombre(clientDomain.getNombre());
        clientEntity.setIdentificacion(clientDomain.getIdentificacion());
        clientEntity.setDireccion(clientDomain.getDireccion());
        clientEntity.setTelefono(clientDomain.getTelefono());
        clientEntity.setContrasenia(clientDomain.getContrasenia());
        clientEntity.setEstado(StatusRecord.ACTIVE.getValue());
        clientEntity.setGenero(clientDomain.getGenero());
        clientEntity.setEdad(clientDomain.getEdad());
        clientRepository.save(clientEntity);
    }

    /**
     * Elimina (desactiva) un cliente en la base de datos.
     *
     * @param id el ID del cliente a eliminar
     */
    @Override
    public void delete(Integer id) {
        ClientEntity clientEntity = clientRepository.findByCclienteAndEstadoTrue(id).orElse(null);
        if (clientEntity != null) {
            clientEntity.setEstado(StatusRecord.INACTIVE.getValue());
            clientRepository.save(clientEntity);
        }
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id el ID del cliente a buscar
     * @return el dominio del cliente encontrado o null si no se encuentra
     */
    @Override
    public ClientDomain findById(Integer id) {
        ClientEntity clientEntity = clientRepository.findByCclienteAndEstadoTrue(id).orElse(null);
        if (clientEntity != null) {
            ClientDomain clientDomain = new ClientDomain();
            clientDomain.setEstado(clientEntity.isEstado());
            clientDomain.setCpersona(clientEntity.getCpersona());
            clientDomain.setCcliente(clientEntity.getCcliente());
            clientDomain.setNombre(clientEntity.getNombre());
            clientDomain.setIdentificacion(clientEntity.getIdentificacion());
            clientDomain.setDireccion(clientEntity.getDireccion());
            clientDomain.setTelefono(clientEntity.getTelefono());
            clientDomain.setContrasenia(clientEntity.getContrasenia());
            clientDomain.setGenero(clientEntity.getGenero());
            clientDomain.setEdad(clientEntity.getEdad());
            return clientDomain;
        }
        return null;
    }

}
