package com.nttdata.accountmovements.modules.movements.configuration;

import com.nttdata.accountmovements.modules.movements.application.MovementApplication;
import com.nttdata.accountmovements.modules.movements.domain.MovementService;
import com.nttdata.accountmovements.modules.movements.infrastructure.MovementInfrastructure;
import com.nttdata.accountmovements.modules.movements.infrastructure.MovementRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuracion para los movimientos.
 * Define los beans necesarios para la aplicacion de movimientos.
 */
@Configuration
public class MovementConfiguration {

    private final MovementRepository movementRepository;

    /**
     * Constructor de MovementConfiguration.
     *
     * @param movementRepository Repositorio de movimientos inyectado.
     */
    public MovementConfiguration(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    /**
     * Define el bean para MovementService.
     *
     * @return una instancia de MovementService.
     */
    @Bean
    public MovementService movementService() {
        return new MovementInfrastructure(this.movementRepository);
    }

    /**
     * Define el bean para MovementApplication.
     *
     * @return una instancia de MovementApplication.
     */
    @Bean
    public MovementApplication movementApplication() {
        return new MovementApplication(this.movementService());
    }

}
