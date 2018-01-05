let mysql = require("mysql");

let connection = mysql.createConnection({
    host: 'localhost',
    port: 13306,
    user: 'piwik',
    password: '123456',
    database: 'piwik'
});

connection.connect();

let sql = 'select * from piwik_site'
connection.query(sql, (error, results, fields) => {
    if (error) throw error;
    console.log(results[0].name);
    console.log(fields[0])
});
connection.end();