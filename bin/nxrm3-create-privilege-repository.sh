#!/bin/bash

script_name=create_privilege_repository

priv_name=$1

priv_repository="mvn-releases"
priv_actions="read,browse"

type="repository-view"
format="maven2"
content_selector=


ansible-playbook -v ../playbooks/nxrm3-script-run.yml --extra-vars "script_name=${script_name} nxrm3_privilege_name=${priv_name} nxrm3_repository=${priv_repository} nxrm3_privilege_description=${priv_name} nxrm3_actions=${priv_actions} nxrm3_privilege_type=${type} nxrm3_format=${format} nxrm3_privilege_content_selector=${content_selector}"

