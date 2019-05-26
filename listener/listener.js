
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
    const payload = req.body
    console.log(payload);

    if (payload.action === 'CREATED'){
      if (payload.component.format === 'docker'){
        console.log('docker pull sola.local:18087/' + payload.component.name + ':' + payload.component.version)
      }
      if (payload.component.format === 'maven2'){
        console.log('  <dependency>')
        console.log('    <groupId>' + payload.component.group + '</group>')
        console.log('    <artifactId>' + payload.component.name + '</artifactId>')
        console.log('    <version>' + payload.component.version + '</version>')
        console.log('  </dependency>')
      }
    }

    if (payload.action === 'DELETED'){
      console.log('curl -s -u admin:admin123 -X DELETE http://localhost:8082/service/rest/v1/components/' + payload.component.id)
    }


    // const ls = spawn('ls', ['-lh', '/usr']);

    // ls.stdout.on('data', (data) => {
    //   console.log(`stdout: ${data}`);
    // });

});



