# docker

### [Dockerfile](https://docs.docker.com/engine/reference/builder/#from "docker docs")

1. FROM

    指明当前镜像继承的基镜像。

    ```
    FROM ubuntu
    ```

1. MAINTAINER

    指明当前镜像作者。

    ```
    MAINTAINER zcw
    ```

1. RUN

    在当前镜像上执行Linux命令，RUN是编译(build)的动作。

    ```
    RUN /bin/bash -c "echo hello"
    RUN ["/bin/bash","-c","echo hello"]
    RUN mkdir app
    ```

1. CMD

    指明启动容器是的默认行为，若多条，仅最后一条生效，CMD为运行时(run)的动作。

    ```
    CMD /app/runboot.sh
    docker run -d IMAGE_NAME /app/other.sh      # 启动时可覆盖上一行配置
    ```

1. EXPOSE

    指明镜像运行时的容器必须监听指定的端口，暴露的端口和提示的可不一样,仅用于提示。

    ```
    EXPOSE 8080
    ```

1. ENV

    用于设置环境变量

    ```
    ENV myname=zcw
    ENV myname zcw
    ```

1. ADD

    从当前工作目录复制文件到镜像目录中，只在build时执行。

    ```
    ADD test.jar /app/app.jar
    ```

1. ENTRYPOINT

    可让容器像可执行程序一样接收参数运行，ENTRYPOINT为运行时(run)的动作。

    ```
    ENTRYPOINT ["/bin/echo"]
    ```

1. COPY

1. LABEL

1. STOPSIGNAL

1. USER

1. VOLUME

    将本地文件夹、其它容器文件夹挂载到容器中。

1. WORKDIR

### [rename image]()

```
docker tag IMAGE_ID docker.io/zengchw/NEW_IMAGE_NAME
```

### [login docker hub]()

```
docker login --username=zengchw --password=123456 --email=zcw1994@live.com
```

### [push image to docker hub]()

```
docker push docker.io/zengchw/xx-net
```

### [docker api](https://docs.docker.com/engine/api/v1.29/)

- centos

    1. 编辑/lib/systemd/system/docker.service文件，ExecStart出追加如下参数

        ```
        --tls=false \
        -H unix:///var/run/docker.sock \
        -H tcp://0.0.0.0:2375
        ```

    1. 重启服务    
        ```
        systemctl daemon-reload
        systemctl restart docker.service
        ```

    1. 若docker命令无法使用，在/etc/profile中添加（该问题未遇到）

        ```
        export DOCKER_HOST= 'http://0.0.0.0:2375'
        ```     

- ubuntu

    ```
    TODO
    ```

- mac

    ```
    TODO
    ```

- windows

    ```
    Settings --> General --> Expose daemon ... without TLS
    ```