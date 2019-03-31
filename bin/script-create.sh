#!/bin/bash

jsonFile=$1

userid=admin
passwd=admin123

repoUrl1="http://localhost:8081/service/rest/v1/script/"
repoUrl2="http://localhost:8082/service/rest/v1/script/"

printf "Creating Integration API Script from $jsonFile\n\n"

curl -v -u ${userid}:${passwd} --header "Content-Type: application/json" "${repoUrl1}" -d @$jsonFile
curl -v -u ${userid}:${passwd} --header "Content-Type: application/json" "${repoUrl2}" -d @$jsonFile

