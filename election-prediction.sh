#!/usr/bin/env bash

set -o errexit

source election-prediction-env.sh

build() {
  mvn clean package -DskipTests
  for module in "${OTHER_LANGUAGES_MODULES[@]}"; do
    docker build \
      --network host \
      -t "election-prediction/${module}" \
      -f "${module}/Dockerfile" \
      "${module}"
  done
  for module in "${DOCKERIZE_JAVA_MODULES[@]}"; do
    docker build \
      -t "election-prediction/${module}" \
      -f "${module}/target/${module}/Dockerfile" \
      "${module}/target/${module}"
  done
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
