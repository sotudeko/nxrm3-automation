#!/bin/bash

repoUrl=$1
username=$2
passwd=$3
componentId=$4

endPoint='service/rest/v1/components'

echo "Delete Nexus Repository Manager component\n"
echo

curl -s -u ${username}:${passwd} -X DELETE ${repoUrl}/${endPoint}/${componentId}
echo 'deleted'

exit 0




