package me.juan.amq.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String queueName, Object message) {
        try{
            log.info("Attempting Send message to Queue: "+ queueName);
            String json = new ObjectMapper().writeValueAsString(message);
            jmsTemplate.convertAndSend(queueName, json);
            log.info("Message sent to Queue: "+ queueName);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }
}
