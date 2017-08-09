# jenkins

[README](https://github.com/jenkinsci/docker)

- docker 安装

```
docker run -d --name jenkins \
    -v `pwd`/jenkins_home:/var/jenkins_home \
    -p 10100:8080 \
    -u root \
    jenkins
```

- 修改docker容器内时区

```
rm -f /etc/localtime 
ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
```

- 修改jenkins的时区

```
rm -f /etc/timezone
echo "Asia/Shanghai" >> /etc/timezone
```

- ssh 插件

    系统管理→管理插件→可选插件→Artifact Uploaders→Publish Over SSH