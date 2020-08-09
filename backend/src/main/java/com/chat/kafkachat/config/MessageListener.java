package com.chat.kafkachat.config;

import com.chat.kafkachat.constants.KafkaConstants;
import com.chat.kafkachat.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println("sending via kafka listener the message: " + message.getContent());
        template.convertAndSend(KafkaConstants.TOPIC_GROUP_MESSAGE, message);
    }
}