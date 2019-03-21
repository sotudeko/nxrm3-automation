#!/bin/bash

repository=$1
group=$2
artifact=$3
version=$4
artifact_path=/Users/sotudeko/Development/scan-artifacts/java/webgoat-server-8.0.0.M21.jar
extension=jar

playbook=nxrm3-component-upload

ansible-playbook -v ../playbooks/${playbook}.yml --extra-vars "repository=${repository} group=${group} artifact=${artifact} version=${version} artifact_path=${artifact_path} extension=${extension}"

