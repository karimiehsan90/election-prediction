#!/usr/bin/env bash

set -o errexit
cd $(dirname $0)/..
APP_NAME=crawler-1.0-SNAPSHOT.jar
mkdir -p logs
jar uf lib/$APP_NAME -C conf/ .
cd logs
java -jar ../lib/$APP_NAME
