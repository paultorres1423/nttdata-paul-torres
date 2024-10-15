package com.nttdata.clientperson.modules.client.configuration;

import com.nttdata.clientperson.modules.client.application.ClientApplication;
import com.nttdata.clientperson.modules.client.domain.ClientService;
import com.nttdata.clientperson.modules.client.infrastructure.ClientInfrastructure;
import com.nttdata.clientperson.modules.client.infrastructure.ClientRepository;
import com.nttdata.clientperson.modules.common.RabbitMQSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de Spring para los componentes del cliente.
 */
@Configuration
public class ClientConfiguration {

    private final ClientRepository clientRepository;
    private final RabbitMQSender rabbitMQSender;

    /**
     * Constructor para inyectar dependencias.
     *
     * @param clientRepository Repositorio de clientes.
     * @param rabbitMQSender   Enviador de mensajes RabbitMQ.
     */
    public ClientConfiguration(ClientRepository clientRepository, RabbitMQSender rabbitMQSender) {
        this.clientRepository = clientRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    /**
     * Define el bean para ClientService.
     *
     * @return una instancia de ClientService.
     */
    @Bean
    public ClientService clientService() {
        return new ClientInfrastructure(this.clientRepository);
    }

    /**
     * Define el bean para ClientApplication.
     *
     * @return una instancia de ClientApplication.
     */
    @Bean
    public ClientApplication clientApplication() {
        return new ClientApplication(this.clientService(), this.rabbitMQSender);
    }

}
