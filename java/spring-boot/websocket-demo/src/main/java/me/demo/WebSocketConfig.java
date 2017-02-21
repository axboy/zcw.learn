package me.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by zcw on 2016/12/29.
 */
@Configuration
@EnableWebSocketMessageBroker   //开启使用STOMP协议，控制器支持使用@MessageMapping
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    //注册STOMP协议的节点（EndPoint），并映射到指定的URL
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //允许使用SocketJs方式访问，访问点为hello，允许跨域
        stompEndpointRegistry.addEndpoint("/hello").withSockJS();
        stompEndpointRegistry.addEndpoint("/endpointChat").setAllowedOrigins("*").withSockJS();
    }

    @Override
    //配置消息代理
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //super.configureMessageBroker(registry);
        //订阅Broker名称；广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic", "/user", "/queue");

        //全局使用的订阅前缀（客户端订阅路径上会体现出来）
        //registry.setApplicationDestinationPrefixes("/app/");

        //点对点使用的订阅前缀，不设置的话，默认也是/user
        registry.setUserDestinationPrefix("/user/");
    }
}
