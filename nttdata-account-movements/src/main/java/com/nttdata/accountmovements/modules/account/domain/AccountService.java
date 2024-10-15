package com.nttdata.accountmovements.modules.account.domain;

/**
 * Interface que define los metodos para el servicio de cuentas.
 */
public interface AccountService {

    void insert(AccountDomain accountDomain);

    void update(AccountDomain accountDomain);

    void delete(Integer id);

    AccountDomain findById(Integer id);

    Integer getAccountNumber();

}
