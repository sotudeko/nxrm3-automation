#!/bin/bash

repoUrl=$1
username=$2
passwd=$3

endPoint='service/rest/v1/repositories'

echo "Listing Nexus Repository Manager Repositories\n"
echo "- ${repoUrl}"
echo

curl -s -u ${username}:${passwd} -X GET ${repoUrl}/${endPoint} | grep '"name"' | cut -f4 -d'"' 
echo

exit 0





