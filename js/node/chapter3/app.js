let express = require('express');
const PORT = 8080;

let app = express();
app.use(express.static('.'));
app.listen(PORT,()=>{console.log('server is running at localhost:%d', PORT);});

app.get('/hello',(req,resp)=>{resp.send('hello')});