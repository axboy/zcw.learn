let http = require('http');
let httpProxy = require('http-proxy');
const PORT = 8081;

//创建代理服务器对象并监听错误事件
let proxy = httpProxy.createProxyServer();

proxy.on('error', (err, req, resp) => {
    console.log("error"); 
    resp.end();
});

let app = http.createServer( (req,resp) => {
    proxy.web(req, resp, {
        target: "http://localhost:8080"
    });
});

app.listen(PORT, () => {
    console.log(`server is running at localhost:${PORT}`);
});