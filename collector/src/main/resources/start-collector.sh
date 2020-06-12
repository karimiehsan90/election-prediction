#!/usr/bin/env bash

set -o errexit

cd $(dirname $0)/..
APP_NAME=collector-1.0-SNAPSHOT.jar
mkdir -p logs
jar uf lib/$APP_NAME -C conf/ .
cd logs
out_path=$(cat ../conf/collector.properties | grep "collector.hdfs.out.path" | cut -d'=' -f2)
out_dir=$(dirname $out_path)
hadoop fs -mkdir -p $out_dir
hadoop fs -touchz $out_path || true
java -jar ../lib/$APP_NAME
