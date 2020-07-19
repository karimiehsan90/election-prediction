# Election Prediction

This system predicts elections, based on users' emotions.
It detects users' emotions in political tweets that are about some candidates.
It gets score to each emotion and evaluates candidates' scores.
The candidate with more scores has more probability to win in the election.

## Install

To install the project in local, install a web-server like apache tomcat in your host,
then copy required artifacts to the web-server root directory. Then just run the script in the root of the project.

```bash
./election-prediction.sh run-project
```

The script builds the project, docker images, and other artifacts. Then setups two virtual machines with vagrant.
Then runs ansible playbooks in these two VMs.

The ansible-playbook consists of two parts named infrastructure and application.
In the infrastructure part of that, it configures /etc/hosts file, configures a firewall named iptables,
and installs java development kit in all machines.
Then installs redis, docker, zookeeper, kafka, hadoop, prometheus and grafana.
In the application part of the playbook, it copies configuration files and runs our modules.

## Architecture

The project consists of 7 modules. each module runs separately.
Some modules run as a Docker Container, some modules run as a map-reduce job in the Hadoop and
a module directly runs on the host.

The first module that runs as a Docker Container is the Crawler.
It crawls twitter and saves them in a Kafka Topic named tweets.
The Stream Processor module consumes from the Kafka Topic and asks the emotion of that from the Emotion Detector module.
And the end of that, Stream Processor saves the candidate name that the tweet is about him,
the retweets count of the tweet, the favorites count of the tweet, and the emotion of the tweet
in an another Kafka Topic named Hadoop-Data.
The Stream Processor and the Emotion Detector modules run as a Docker Container.

The Collector module consumes from the Hadoop-Data Kafka Topic and saves them into the HDFS.
This module directly runs on the host.
The Reporter module runs on Hadoop as a map-reduce job.
The output of Reporter is the count of tweets that are about some candidate and the count of tweets with each emotion.
And the latest module is the Predictor.
This module runs on Hadoop as a map-reduce job and predicts the election.

There is another module in the code-base named Stream Generator that is for our tests.
