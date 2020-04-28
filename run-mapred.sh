#!/usr/bin/env bash

set -o errexit

DIRECTORY=$(dirname $0)

cd $DIRECTORY
mvn clean package -DskipTests
scp reporter/target/reporter-1.0-SNAPSHOT-jar-with-dependencies.jar \
    vagrant@master:reporter.jar

ssh vagrant@master "
/opt/hadoop/bin/hadoop fs -rm -r /election-prediction/reporter
/opt/hadoop/bin/hadoop jar reporter.jar ir.ac.sbu.data_mining.App
rm part-r-00000
/opt/hadoop/bin/hadoop fs -get /election-prediction/reporter/part-r-00000 .
cat part-r-00000
"
