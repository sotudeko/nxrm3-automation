#!/bin/bash

script_name=$1
role_id=$2
role_name=$3

ansible-playbook -v ../playbooks/nxrm3-script-run.yml --extra-vars "script_name=${script_name} nxrm3_role_id=${role_id} nxrm3_role_name=${role_name}"

