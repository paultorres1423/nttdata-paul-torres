package com.nttdata.accountmovements.modules.common;

import com.nttdata.accountmovements.modules.account.application.AccountApplication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Servicio que consume mensajes de RabbitMQ.
 */
@Service
public class RabbitMQConsumer {

    private final AccountApplication accountApplication;

    /**
     * Constructor que inyecta la dependencia de AccountApplication.
     *
     * @param accountApplication instancia de AccountApplication
     */
    public RabbitMQConsumer(AccountApplication accountApplication) {
        this.accountApplication = accountApplication;
    }

    /**
     * Metodo que escucha mensajes de la cola configurada en RabbitMQ.
     *
     * @param message el mensaje recibido de la cola
     */
    @RabbitListener(queues = RabbitMQConfiguration.QUEUE)
    public void listen(String message) {
        this.accountApplication.createNewAccount(message);
    }

}
