# 指定基础镜像
FROM java:8

# 指定临时目录，springboot内置tomcat默认使用/tmp为工作目录
VOLUME /tmp

# 不解释
RUN mkdir /app
ADD docker-0.0.1-SNAPSHOT.jar /app/app.jar
ADD runboot.sh /app/
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
EXPOSE 8080
CMD /app/runboot.sh