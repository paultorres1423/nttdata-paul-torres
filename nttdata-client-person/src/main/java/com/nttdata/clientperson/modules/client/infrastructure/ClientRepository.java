package com.nttdata.clientperson.modules.client.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad ClientEntity.
 * Proporciona metodos para realizar operaciones CRUD en la base de datos.
 */
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    /**
     * Busca un ClientEntity por su id y estado.
     *
     * @param id el id del cliente
     * @return un Optional que contiene el ClientEntity si se encuentra y su estado es true, de lo contrario un
     * Optional vacio
     */
    Optional<ClientEntity> findByCclienteAndEstadoTrue(Integer id);
}
