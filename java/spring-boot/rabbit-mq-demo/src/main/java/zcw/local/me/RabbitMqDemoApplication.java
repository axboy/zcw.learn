package zcw.local.me;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitMqDemoApplication implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;  //注入默认的rabbitTemplate

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqDemoApplication.class, args);
    }

//    @Bean        //定义消息队列，即消息目的地
//    public Queue zcwQueue() {
//        return new Queue("test-queue");
//    }

    @Override
    public void run(String... strings) throws Exception {
        //向队列发送消息
        rabbitTemplate.convertAndSend("test-queue", "test message from rabbit mq");
    }
}
