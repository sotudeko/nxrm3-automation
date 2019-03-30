#!/bin/bash

script_name=create_role
role_id=$1
role_name=$2

ansible-playbook -v ../playbooks/nxrm3-script-run.yml --extra-vars "script_name=${script_name} nxrm3_role_id=${role_id} nxrm3_role_name=${role_name} nxrm3_privileges=nx-all nxrm3_roles=nx-admin"

