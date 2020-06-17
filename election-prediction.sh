#!/usr/bin/env bash

set -o errexit

build() {
  docker build \
    -t election-prediction/provisioning \
    -f provisioning/Dockerfile \
    provisioning
}

run-playbook() {
  inventory="$1"
  playbook="$2"
  docker run -it \
    --net host \
    --rm \
    election-prediction/provisioning \
    "${inventory}" \
    "${playbook}"
}

run-project() {
  build
  ./provisioning/vagrant/setup-vm.sh
  run-playbook local
}

parse-args() {
  METHOD=${1}
}

main() {
  parse-args "${@}"
  shift
  $METHOD "${@}"
}

main "${@}"
