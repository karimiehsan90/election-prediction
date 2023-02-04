FROM brunneis/python:3.8.0-ubuntu-bionic

COPY requirements.txt /requirements.txt

RUN pip install -r /requirements.txt \
    && rm /requirements.txt \
    && mkdir /opt/election-prediction

COPY sources.list /etc/apt/sources.list

RUN apt update \
    && apt install -y --no-install-recommends sshpass

COPY ansible /opt/election-prediction/

COPY run.sh /run.sh

RUN chmod +x /run.sh

WORKDIR /opt/election-prediction

ENTRYPOINT ["/run.sh"]
