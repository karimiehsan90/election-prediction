FROM m.docker-registry.ir/python:3.6.9

COPY requirements.txt /requirements.txt

RUN pip install -r /requirements.txt \
    && rm /requirements.txt \
    && mkdir /opt/election-prediction

RUN apt update \
    && apt install -y --no-install-recommends sshpass

COPY ansible /opt/election-prediction/

COPY run.sh /run.sh

RUN chmod +x /run.sh

WORKDIR /opt/election-prediction

ENTRYPOINT ["/run.sh"]
