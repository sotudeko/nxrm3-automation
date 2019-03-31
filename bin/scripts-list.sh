#!/bin/bash

printf "Listing Integration API Scripts\n"

curl -u admin:admin123 -X GET http://localhost:8081/service/rest/v1/script > /tmp/localhost8081.lst
curl -u admin:admin123 -X GET http://localhost:8082/service/rest/v1/script > /tmp/localhost8082.lst

echo "-> /tmp/localhost8081.lst"
echo "-> /tmp/localhost8082.lst"
