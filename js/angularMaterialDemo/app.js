let express = require('express');
const PORT = 4000;

let app = express();
app.use(express.static('.'));
app.listen(PORT, () => { console.log('server is running at localhost:%d', PORT); });

app.get('/', (req, resp) => { resp.sendFile('/index.html') });