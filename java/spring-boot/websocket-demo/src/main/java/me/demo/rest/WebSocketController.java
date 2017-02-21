package me.demo.rest;

import me.demo.domain.Chat;
import me.demo.domain.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by zcw on 2016/12/29.
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;    //用于点对点消息的发送

    @MessageMapping("/welcome")     //类似@RequetMapping
    @SendTo("/topic/getResponse")   //发送到Broker下的指定订阅路径；当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    public Msg say(Msg msg) {
        System.out.println("client msg:" + msg.getMsg());
        msg.setMsg("welcome:" + msg.getMsg());
        return msg;
    }

    @MessageMapping("/chat")
    public void chat(Chat chat) {
        System.out.println("to:" + chat.getToUser());
        System.out.println("msg:" + chat.getMsg());
        messagingTemplate.convertAndSendToUser(chat.getToUser(), "/message", chat.getMsg());
    }
}
