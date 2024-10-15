package com.nttdata.accountmovements.modules.account.application;

import com.nttdata.accountmovements.modules.account.application.dto.AccountFindByIdDto;
import com.nttdata.accountmovements.modules.account.domain.AccountDomain;
import com.nttdata.accountmovements.modules.account.domain.AccountService;
import com.nttdata.accountmovements.modules.common.ApiException;

import java.math.BigDecimal;

/**
 * Clase AccountApplication que maneja la logica de negocio para las cuentas.
 */
public class AccountApplication {

    private static final String ACCOUNT_NOT_FOUND = "Account not found";
    private static final String ACCOUNT_ALREADY_EXISTS = "Account already exists";
    private final AccountService accountService;

    /**
     * Constructor de la clase AccountApplication.
     *
     * @param accountService Servicio de cuentas utilizado para operaciones de negocio.
     */
    public AccountApplication(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Inserta una nueva cuenta en el sistema.
     *
     * @param accountDomain Objeto AccountDomain que contiene los datos de la cuenta a insertar.
     * @throws ApiException Si la cuenta ya existe.
     */
    public void insert(AccountDomain accountDomain) {
        accountDomain.validate();
        AccountDomain accountDomainUpdate = this.accountService.findById(accountDomain.getCnumerocuenta());
        if (accountDomainUpdate == null) {
            this.accountService.insert(accountDomain);
        } else {
            throw new ApiException(ACCOUNT_ALREADY_EXISTS);
        }
    }

    /**
     * Actualiza una cuenta existente en el sistema.
     *
     * @param accountDomain Objeto AccountDomain que contiene los datos actualizados de la cuenta.
     * @throws ApiException Si la cuenta no se encuentra.
     */
    public void update(AccountDomain accountDomain) {
        AccountDomain accountDomainUpdate = this.accountService.findById(accountDomain.getCnumerocuenta());
        if (accountDomainUpdate == null) throw new ApiException(ACCOUNT_NOT_FOUND);
        accountDomainUpdate.setTipocuenta(accountDomain.getTipocuenta());
        accountDomainUpdate.setSaldoinicial(accountDomain.getSaldoinicial());
        accountDomainUpdate.validate();
        this.accountService.update(accountDomainUpdate);
    }

    /**
     * Elimina una cuenta del sistema.
     *
     * @param id Identificador de la cuenta a eliminar.
     * @throws ApiException Si la cuenta no se encuentra.
     */
    public void delete(Integer id) {
        AccountDomain accountDomain = this.accountService.findById(id);
        if (accountDomain == null) throw new ApiException(ACCOUNT_NOT_FOUND);
        this.accountService.delete(id);
    }

    /**
     * Busca una cuenta por su identificador.
     *
     * @param id Identificador de la cuenta a buscar.
     * @return Objeto AccountFindByIdDto que contiene los datos de la cuenta encontrada.
     * @throws ApiException Si la cuenta no se encuentra.
     */
    public AccountFindByIdDto findById(Integer id) {
        AccountDomain accountDomain = this.accountService.findById(id);
        if (accountDomain == null) throw new ApiException(ACCOUNT_NOT_FOUND);
        return new AccountFindByIdDto(
                accountDomain.getCnumerocuenta(),
                accountDomain.getCcliente(),
                accountDomain.getTipocuenta(),
                accountDomain.getSaldoinicial(),
                accountDomain.isEstado()
        );
    }

    /**
     * Crea una nueva cuenta en el sistema.
     *
     * @param message Mensaje que contiene el identificador del cliente.
     */
    public void createNewAccount(String message) {
        AccountDomain accountDomain = new AccountDomain();
        accountDomain.setCnumerocuenta(this.accountService.getAccountNumber());
        accountDomain.setCcliente(Integer.parseInt(message));
        accountDomain.setTipocuenta("Ahorros");
        accountDomain.setSaldoinicial(BigDecimal.ZERO);
        this.insert(accountDomain);
    }

}
