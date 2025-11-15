package com.fiap.gs_synapse.messaging;

import com.fiap.gs_synapse.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    public EmailQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_EMAIL, mensagem);
        System.out.println("[EMAIL-QUEUE] mensagem enviada: " + mensagem);
    }
}
