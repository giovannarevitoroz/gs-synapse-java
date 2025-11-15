package com.fiap.gs_synapse.messaging;

import com.fiap.gs_synapse.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailQueueListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_EMAIL)
    public void handle(String message) {
        System.out.println("[EMAIL-QUEUE] mensagem recebida: " + message);
        // Processamento assíncrono aqui
    }
}
