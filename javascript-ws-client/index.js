var http = require('http');
var fs = require('fs');

http.createServer((request, response) => {
    fs.readFile('./index.html', (err, content) => {
        response.writeHead('200', { 'Content-Type': 'text/html' });
        response.end(content, 'utf-8');
    });
}).listen(8080);
