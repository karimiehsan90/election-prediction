FROM mcr.microsoft.com/java/jdk:8u192-zulu-ubuntu

COPY . /opt/election-prediction

WORKDIR /opt/election-prediction

RUN chmod +x /opt/election-prediction/bin/start-stream-processor.sh

ENTRYPOINT ["/opt/election-prediction/bin/start-stream-processor.sh"]
