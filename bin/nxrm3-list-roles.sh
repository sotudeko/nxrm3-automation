#!/bin/bash

playbook=nxrm3-script-run.yml
script_name=list_roles

ansible-playbook  ../playbooks/${playbook} --extra-vars "script_name=${script_name}"
