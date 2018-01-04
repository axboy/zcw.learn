//引入http模块，并赋值给http变量
let http = require("http");
const port = 8080;

http.createServer((req,resp)=>{
    //写入http头部，状态码200，内容类型，text/plain
    resp.writeHead(200,{'Content-type':'text/plain'});
    resp.end('hello nodejs');
}).listen(port);

console.log(`Server runnint at http://localhost:${port}/`);