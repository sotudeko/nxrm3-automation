#!/bin/bash

printf "Listing Integration API Scripts\n"

curl -u admin:admin123 -X GET http://localhost:8081/service/rest/v1/script
