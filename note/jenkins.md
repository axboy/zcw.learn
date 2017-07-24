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

- ssh 插件

    系统管理→管理插件→可选插件→Artifact Uploaders→Publish Over SSH