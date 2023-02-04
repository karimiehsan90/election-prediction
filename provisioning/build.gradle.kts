plugins {
    id("com.bmuschko.docker-remote-api") version "6.7.0"
}

import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage;

tasks.create("build", DockerBuildImage::class) {
    inputDir.set(file("."))
    images.add("election-prediction/provisioning")
}
