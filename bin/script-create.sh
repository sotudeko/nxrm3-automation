#!/bin/bash

jsonFile=$1

userid=admin
passwd=admin123
repoUrl="http://localhost:8081/service/rest/v1/script/"

printf "Creating Integration API Script from $jsonFile\n\n"

curl -v -u ${userid}:${passwd} --header "Content-Type: application/json" "${repoUrl}" -d @$jsonFile

