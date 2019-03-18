#!/bin/bash

role_id=$1
role_name=$2

ansible-playbook ../playbooks/create-nxrm3-role.yml --extra-vars "nxrm3_role_id=${role_id} nxrm3_role_name=${role_name}"

