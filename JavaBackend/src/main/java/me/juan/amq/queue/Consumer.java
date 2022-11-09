package me.juan.amq.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.juan.amq.entity.AmqResponse;
import me.juan.amq.entity.Post;
import me.juan.amq.queue.routes.AmqQueueRoute;
import me.juan.amq.service.PostService;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
@Slf4j
public class Consumer implements MessageListener {

    @Autowired
    private PostService postService;

    @Autowired
    private Producer producer;

    @Override
    @JmsListener(destination = AmqQueueRoute.SERVICE_TO_BACK)
    public void onMessage(Message message) {
        try {
            String json = toText(message);
            ObjectMapper mapper = new ObjectMapper();
            AmqResponse<Post> amqResponse = mapper.readValue(json, AmqResponse.class);
            if(amqResponse.getCommand().equals("get")) {
                producer.sendMessage(AmqQueueRoute.BACK_TO_SERVICE, postService.findAll());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toText(Message message) throws JMSException {
        String text = null;
        if (message instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
            text = amqMessage.getText();
        } else {
            BytesMessage bm = (BytesMessage) message;
            byte[] data = new byte[(int) bm.getBodyLength()];
            bm.readBytes(data);
            text = new String(data);
        }
        log.info("Message received: " + text);
        return text;
    }


}