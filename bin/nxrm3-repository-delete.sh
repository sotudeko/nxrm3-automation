#!/bin/bash

repoUrl=http://localhost:8081
username=admin
passwd=admin123
repo=$1

endPoint="service/rest/beta/repositories/${repo}"

echo "Deleting ${repo} repository"
echo

curl -u ${username}:${passwd} -X DELETE -H "accept:application/json" ${repoUrl}/${endPoint} 
echo

exit 0





