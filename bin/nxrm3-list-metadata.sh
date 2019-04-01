#!/bin/bash

metadata=$1
playbook=nxrm3-script-run.yml
script_name=list_${metadata}

ansible-playbook  ../playbooks/${playbook} --extra-vars "script_name=${script_name}"
