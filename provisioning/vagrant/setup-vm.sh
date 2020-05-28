#!/usr/bin/env bash

set -o errexit

DIRECTORY=$PWD/$(dirname "${0}")
VAGRANT_DIRECTORY=~/vagrant
VAGRANT_FILE_NAME=Vagrantfile

mkdir -p ${VAGRANT_DIRECTORY}

_contains() {
  list=${1}
  variable=${2}
  CONTAINS="0"
  for item in ${list}; do
    if [ "${variable}" == "${item}" ]; then
      CONTAINS="1"
    fi
  done
}

change-dir() {
  cd ${VAGRANT_DIRECTORY}
}

destroy() {
  vagrant_directory_files=$(ls ${VAGRANT_DIRECTORY})
  _contains "${vagrant_directory_files}" "${VAGRANT_FILE_NAME}"
  if [ "${CONTAINS}" == "1" ]; then
    vagrant destroy -f
  fi
}

setup-vm() {
  cp "${DIRECTORY}/Vagrantfile" "${VAGRANT_DIRECTORY}"
  vagrant up
}

check-connection() {
  exec 3<>/dev/tcp/192.168.100.101/22
  exec 3<>/dev/tcp/192.168.100.102/22
}

main() {
  change-dir
  destroy
  setup-vm
  check-connection
}

main
