### spring-boot项目docker部署demo

mvn package docker:build -DpushImage


- TODO
    - Windows有如下问题，待处理
    
    ```aidl
    Failed to execute goal com.spotify:docker-maven-plugin:1.0.0:build (default-cli)
        on project docker: Exception caught: java.util.concurrent.ExecutionException: com.spotify.
        docker.client.shaded.javax.ws.rs.ProcessingException: org.apache.http.conn.HttpHostConnectException:
        Connect to localhost:2375 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: connect -> [Help 1]
    ```