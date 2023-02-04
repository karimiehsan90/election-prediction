#!/usr/bin/env bash

set -o errexit

build() {
  ./gradlew build
  ./gradlew jibDockerBuild
}

push() {
  registry="${1}"
  docker tag election-prediction/crawler "${registry}/election-prediction/crawler"
  docker push "${registry}/election-prediction/crawler"
}

run-playbook() {
  inventory="$1"
  playbook="$2"
  docker run -it \
    --net host \
    -v /var/run/docker.sock:/var/run/docker.sock \
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
