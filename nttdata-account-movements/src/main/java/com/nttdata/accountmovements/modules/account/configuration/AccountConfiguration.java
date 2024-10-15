package com.nttdata.accountmovements.modules.account.configuration;

import com.nttdata.accountmovements.modules.account.application.AccountApplication;
import com.nttdata.accountmovements.modules.account.domain.AccountService;
import com.nttdata.accountmovements.modules.account.infrastructure.AccountInfrastructure;
import com.nttdata.accountmovements.modules.account.infrastructure.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de la aplicacion para el modulo de cuentas.
 */
@Configuration
public class AccountConfiguration {

    private final AccountRepository accountRepository;

    /**
     * Constructor de la clase AccountConfiguration.
     *
     * @param accountRepository Repositorio de cuentas inyectado.
     */
    public AccountConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Define el bean para AccountService.
     *
     * @return una instancia de AccountService.
     */
    @Bean
    public AccountService accountService() {
        return new AccountInfrastructure(this.accountRepository);
    }

    /**
     * Define el bean para AccountApplication.
     *
     * @return una instancia de AccountApplication.
     */
    @Bean
    public AccountApplication accountApplication() {
        return new AccountApplication(this.accountService());
    }

}
