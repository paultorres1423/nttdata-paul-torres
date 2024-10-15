package com.nttdata.accountmovements.modules.common;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de RabbitMQ para la aplicacion.
 * Esta clase define la configuracion necesaria para crear y gestionar una cola en RabbitMQ.
 */
@Configuration
public class RabbitMQConfiguration {

    // Nombre de la cola
    public static final String QUEUE = "myQueue";

    /**
     * Define un bean para la cola de RabbitMQ.
     *
     * @return una nueva instancia de la cola con el nombre especificado.
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

}
