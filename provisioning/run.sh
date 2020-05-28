#!/usr/bin/env bash

set -o errexit

inventory="${1:local}"
ansible-playbook playbook.yml -i "inventories/${inventory}/"
