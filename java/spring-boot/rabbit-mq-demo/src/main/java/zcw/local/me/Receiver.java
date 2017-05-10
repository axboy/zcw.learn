package zcw.local.me;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "test-queue")
    public void receiveMessage(String msg) {
        System.out.println("received:" + msg);
    }
}
