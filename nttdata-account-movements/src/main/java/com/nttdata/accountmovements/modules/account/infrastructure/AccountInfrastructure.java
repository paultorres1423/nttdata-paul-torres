package com.nttdata.accountmovements.modules.account.infrastructure;

import com.nttdata.accountmovements.modules.account.domain.AccountDomain;
import com.nttdata.accountmovements.modules.account.domain.AccountService;
import com.nttdata.accountmovements.modules.common.StatusRecord;

/**
 * Implementacion de la interfaz AccountService para manejar la infraestructura de cuentas.
 */
public class AccountInfrastructure implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * Constructor para inicializar AccountInfrastructure con un repositorio de cuentas.
     *
     * @param accountRepository el repositorio de cuentas
     */
    public AccountInfrastructure(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Inserta una nueva cuenta en el repositorio.
     *
     * @param accountDomain el dominio de la cuenta a insertar
     */
    @Override
    public void insert(AccountDomain accountDomain) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setCnumerocuenta(accountDomain.getCnumerocuenta());
        accountEntity.setCcliente(accountDomain.getCcliente());
        accountEntity.setTipocuenta(accountDomain.getTipocuenta());
        accountEntity.setSaldoinicial(accountDomain.getSaldoinicial());
        accountEntity.setEstado(StatusRecord.ACTIVE.getValue());
        accountRepository.save(accountEntity);
    }

    /**
     * Actualiza una cuenta existente en el repositorio.
     *
     * @param accountDomain el dominio de la cuenta a actualizar
     */
    @Override
    public void update(AccountDomain accountDomain) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setCnumerocuenta(accountDomain.getCnumerocuenta());
        accountEntity.setCcliente(accountDomain.getCcliente());
        accountEntity.setTipocuenta(accountDomain.getTipocuenta());
        accountEntity.setSaldoinicial(accountDomain.getSaldoinicial());
        accountEntity.setEstado(StatusRecord.ACTIVE.getValue());
        accountRepository.save(accountEntity);
    }

    /**
     * Elimina (desactiva) una cuenta en el repositorio.
     *
     * @param id el identificador de la cuenta a eliminar
     */
    @Override
    public void delete(Integer id) {
        AccountEntity accountEntity = accountRepository.findByCnumerocuentaAndEstadoTrue(id).orElse(null);
        if (accountEntity != null) {
            accountEntity.setEstado(StatusRecord.INACTIVE.getValue());
            accountRepository.save(accountEntity);
        }
    }

    /**
     * Busca una cuenta por su identificador.
     *
     * @param id el identificador de la cuenta a buscar
     * @return el dominio de la cuenta encontrada, o null si no se encuentra
     */
    @Override
    public AccountDomain findById(Integer id) {
        AccountEntity accountEntity = accountRepository.findByCnumerocuentaAndEstadoTrue(id).orElse(null);
        if (accountEntity != null) {
            AccountDomain accountDomain = new AccountDomain();
            accountDomain.setCnumerocuenta(accountEntity.getCnumerocuenta());
            accountDomain.setCcliente(accountEntity.getCcliente());
            accountDomain.setTipocuenta(accountEntity.getTipocuenta());
            accountDomain.setSaldoinicial(accountEntity.getSaldoinicial());
            accountDomain.setEstado(accountEntity.isEstado());
            return accountDomain;
        }
        return null;
    }

    /**
     * Obtiene el siguiente número de cuenta disponible.
     *
     * @return el siguiente número de cuenta disponible
     */
    @Override
    public Integer getAccountNumber() {
        return this.accountRepository.getAccountNumber();
    }

}
