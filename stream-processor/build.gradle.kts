plugins {
    id("ir.ac.sbu.data_mining.java-conventions")
    id("com.google.cloud.tools.jib") version "3.3.1"
}

dependencies {
    api("org.apache.kafka:kafka-clients:2.3.0")
    api("com.fasterxml.jackson.core:jackson-core:2.10.3")
    api("com.fasterxml.jackson.core:jackson-databind:2.10.3")
    api("com.fasterxml.jackson.core:jackson-annotations:2.10.3")
    api("org.apache.httpcomponents:httpclient:4.5.10")
    api("com.sparkjava:spark-core:2.8.0")
}

description = "stream-processor"

jib {
    from {
        image = "mcr.microsoft.com/java/jdk:8u192-zulu-ubuntu"
    }

    container {
        mainClass = "ir.ac.sbu.data_mining.App"
    }

    to {
        image = "election-prediction/stream-processor"
    }
}
