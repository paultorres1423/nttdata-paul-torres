package com.nttdata.clientperson.modules.common;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de RabbitMQ para la aplicacion.
 */
@Configuration
public class RabbitMQConfiguration {

    // Nombre de la cola
    public static final String QUEUE = "myQueue";
    // Nombre del intercambio
    public static final String EXCHANGE = "myExchange";
    // Clave de enrutamiento
    public static final String ROUTING_KEY = "myRoutingKey";

    /**
     * Define una cola en RabbitMQ.
     *
     * @return una nueva instancia de Queue con el nombre especificado.
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }


    /**
     * Define un intercambio de tipo Topic en RabbitMQ.
     *
     * @return una nueva instancia de TopicExchange con el nombre especificado.
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    /**
     * Define una vinculacion entre la cola y el intercambio usando una clave de enrutamiento.
     *
     * @param queue    la cola a vincular.
     * @param exchange el intercambio al cual vincular la cola.
     * @return una nueva instancia de Binding que vincula la cola al intercambio con la clave de enrutamiento
     * especificada.
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

}
