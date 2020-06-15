#!/usr/bin/env bash

set -o errexit

DIRECTORY=$(dirname $0)

cd $DIRECTORY
MODULE=${1}
mvn clean package -DskipTests
scp ${MODULE}/target/${MODULE}-1.0-SNAPSHOT-jar-with-dependencies.jar \
    vagrant@master:${MODULE}.jar

ssh vagrant@master "
/opt/hadoop/bin/hadoop fs -rm -r /election-prediction/${MODULE}
/opt/hadoop/bin/hadoop jar ${MODULE}.jar ir.ac.sbu.data_mining.App
rm part-r-00000
/opt/hadoop/bin/hadoop fs -get /election-prediction/${MODULE}/part-r-00000 .
cat part-r-00000
"
