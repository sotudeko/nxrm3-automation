
'use strict';
 
const express = require('express');
const bodyParser = require('body-parser');
const { spawn } = require('child_process');

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

const server = app.listen(3007, () => console.log('[nxrm3-listener] listening on port 3007'));

app.post('/', (req, res) => {
    console.log(req.body);

    const ls = spawn('ls', ['-lh', '/usr']);
    ls.stdout.on('data', (data) => {
      console.log(`stdout: ${data}`);
    });

});



