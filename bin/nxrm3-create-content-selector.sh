#!/bin/bash

script_name=create_content_selector
cs_name=$1

ansible-playbook -v ../playbooks/nxrm3-script-run.yml --extra-vars "script_name=${script_name} nxrm3_cs_name=${cs_name}"

