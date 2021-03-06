# [XX-Net](https://github.com/XX-net/XX-Net/wiki "wiki")

- Dockerfile

```
FROM ubuntu

RUN apt-get update && \
	apt-get install -y wget && \
	apt-get install -y unzip && \
	mkdir /data && \
	wget https://codeload.github.com/XX-net/XX-Net/zip/3.3.1 -O /data/xx-net.zip && \
	unzip /data/xx-net.zip -d /data/ && \
	apt-get install -y python-openssl && \
	apt-get install -y libffi-dev && \
	apt-get install -y python-gtk2 && \
	apt-get install -y python-appindicator && \
	apt-get install -y libnss3-tools

VOLUME /data/XX-Net-3.3.1/data
EXPOSE 8085 8086 8087
CMD /data/XX-Net-3.3.1/start
```

- build

```sh
docker build . -t xx-net
```

- run

```sh
docker run -d --name xx-net \
    -p 8085-8087:8085-8087 \
    -v `pwd`/data:/data/XX-Net-3.3.1/data \
    xx-net
```

- allow remote connect

```
编辑容器内的/data/XX-Net-3.3.1/data/launcher/config.yaml文件，
把allow_remote_connect的值改为1，
重启容器。
```

- [for other device](https://github.com/XX-net/XX-Net/wiki/%E4%B8%BA%E5%85%B6%E4%BB%96%E8%AE%BE%E5%A4%87%E6%8F%90%E4%BE%9B%E4%BB%A3%E7%90%86%E6%9C%8D%E5%8A%A1 "docs")

    编辑容器内的/data/XX-Net-3.3.1/data/gae_proxy/config.ini文件，没有就新建。

```
[proxy]
enable = 0
type = HTTP
host =
port = 0
user =
passwd =

[google_ip]

[listen]
ip = 0.0.0.0
port = 8087
visible = 1
debuginfo = 0
```

- 直接从docker hub获取镜像

```
docker pull zengchw/xx-net
```