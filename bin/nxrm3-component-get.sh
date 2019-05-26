#!/bin/bash

repoUrl=$1
username=$2
passwd=$3
componentId=$4

endPoint='service/rest/v1/components'

echo "Get Nexus Repository Manager component\n"
echo

curl -s -u ${username}:${passwd} -X GET ${repoUrl}/${endPoint}/${componentId}
echo

exit 0




