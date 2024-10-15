package com.nttdata.accountmovements.modules.account.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de cuentas para manejar la persistencia de cuentas.
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    /**
     * Busca una cuenta por su numero de cuenta y estado activo.
     *
     * @param id el numero de cuenta
     * @return un Optional que contiene la entidad de la cuenta si se encuentra, o un Optional vacio si no
     */
    Optional<AccountEntity> findByCnumerocuentaAndEstadoTrue(Integer id);

    /**
     * Obtiene el siguiente numero de cuenta disponible.
     *
     * @return el siguiente numero de cuenta disponible
     */
    @Query("select max(tcue.cnumerocuenta) + 1 from AccountEntity tcue")
    Integer getAccountNumber();
}
