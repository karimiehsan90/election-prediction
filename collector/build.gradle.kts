plugins {
    id("ir.ac.sbu.data_mining.java-conventions")
}

dependencies {
    api("org.apache.kafka:kafka-clients:2.3.0")
    api("org.apache.hadoop:hadoop-client:2.7.7")
    api("com.sparkjava:spark-core:2.8.0")
}

description = "collector"
