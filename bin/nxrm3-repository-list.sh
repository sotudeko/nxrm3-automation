#!/bin/bash

repoUrl=$1
username=$2
passwd=$3

if [ -z ${repoUrl} ]; then repoUrl="http://localhost:8081"; fi
if [ -z ${username} ]; then username="admin"; fi
if [ -z ${passwd} ]; then passwd="admin123"; fi

endPoint='service/rest/v1/repositories'

echo "Listing Nexus Repository Manager Repositories\n"
echo "- ${repoUrl}"
echo

curl -s -u ${username}:${passwd} -X GET ${repoUrl}/${endPoint} | grep '"name"' | cut -f4 -d'"' 
echo

exit 0





