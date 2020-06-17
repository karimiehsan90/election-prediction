#!/usr/bin/env bash

set -o errexit

inventory="${1:local}"
playbook="${2:main}"
ansible-playbook "playbooks/${playbook}.yml" -i "inventories/${inventory}/"
