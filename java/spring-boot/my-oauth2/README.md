# 运行

使用不同域名测试，hosts做了如下修改
```
    127.0.0.1   a.localhost     # authorization server
    127.0.0.1   r.localhost     # resource server
```

- 启动
```
    cd ./authorization-server/
    mvn spring-boot:run

    cd ./resource-server/
    mvn spring-boot:run
```

- 验证码授权模式(authorization_code)
    1. 浏览器打开http://a.localhost:10010/check,这里会判断是否登录，未登录自动跳去登录页
    2. 登录成功后，自动重定向到授权页，
       http://a.localhost:10010/oauth/authorize?response_type=code&client_id=my-client&redirect_uri=http://a.localhost:10010，
        授权,取得code
    2. 控制台输入
    
        ```
            curl -d grant_type=authorization_code \
                 -d redirect_uri=http://a.localhost:10010 \             //该地址要和前一步授权时的匹配
                 -d client_id=my-client -d code=xxxxx \                 //上一部取得的code替换xxxxxx
                  http://my-client:my-secret@a.localhost:10010/oauth/token
        ```
        
        取得token，格式如下
        
        ```
            {
                "access_token":"52b2a108-1545-4073-8525-30b2ab104cdc",
                "token_type":"bearer",
                "refresh_token":"d48a354f-f0f3-49f8-9c02-4f8870db5c7c",
                "expires_in":3599,
                "scope":"login"
            }
        ```

- 简化模式(implicit)

- 密码模式(password)

- 客户端模式(client_credentials)


# 参考博客

[btpka 3](https://github.com/btpka3/btpka3.github.com/tree/master/java/spring/first-spring-security-oauth2)

[理解OAuth 2.0 阮一峰](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)
