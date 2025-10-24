package proder;

import com.scy.modles.messcenter.MessageCommen;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProder {
    @Autowired
    RabbitTemplate rabbitTemplate;


    public void Producer(MessageCommen message) {
        //使用rabbitTemplate发送消息
        rabbitTemplate.convertAndSend(message);
    }

}
