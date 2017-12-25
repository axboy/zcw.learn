# docker运行idea激活服务器

- 编译

```sh
docker build . -t local/idea
```

- 运行

```sh
docker run -d --name idea \
    -p 1017:1017 \
    local/idea
```