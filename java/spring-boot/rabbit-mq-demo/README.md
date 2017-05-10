异步消息有两个重要概念，消息代理（message broker）和目的地（destination）。
    当消息发送者发送消息之后，消息将由消息代理接管，消息代理保证消息传递到指定的目的地。

异步消息有两种形式的目的地：队列（queue）和主题（topic）。
    队列用于点对点的消息通信
    主题用于发布/订阅式的消息通信
    
docker安装ActiveMQ
    
    ```
    docker run -d -p 61616:61616 \
        -p 8161:8161 \
        cloudesire/acticemq
    ```
    61616是消息代理端口，8161是ActiveMQ的界面管理端口，账号密码默认admin/admin

docker安装RabbitMQ

    ```
    docker run -d -p 5672:5672 \
        -p 15672:15672 \
        rabbitmq:3-managemnent
    ```
    5672是消息代理端口，15672是web管理界面端口