#!/bin/bash

group=$1
artifact=$2
version=$3
extension=$4

outdir="./downloads"
mkdir -p ${outdir}/${group}

#mvn org.apache.maven.plugins:maven-dependency-plugin:3.0.1:copy -Dartifact=log4j:log4j:1.2.17:jar -DoutputDirectory=./
mvn org.apache.maven.plugins:maven-dependency-plugin:3.0.1:copy -Dartifact=${group}:${artifact}:${version}:${extension} -DoutputDirectory=${outdir}

