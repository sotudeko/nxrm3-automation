#!/bin/bash

group=$1
artifact=$2
version=$3
path=$4
extension=$5
repository=$6

playbook=nxrm3-maven-component-upload

ansible-playbook ../playbooks/${playbook}.yml --extra-vars @data/component.json



