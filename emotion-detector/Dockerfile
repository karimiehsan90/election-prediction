FROM m.docker-registry.ir/python:3.6.9

COPY requirements.txt /requirements.txt

RUN pip install -r /requirements.txt

ARG DATASET_URL=main:8080/emotion.tar.gz

RUN apt update \
    && apt install -y --no-install-recommends \
        curl \
    && curl -L ${DATASET_URL} -o /training.tar.gz \
    && tar -xvzf /training.tar.gz \
    && rm /training.tar.gz

RUN ls emotion

COPY main.py /main.py

ENTRYPOINT ["python", "/main.py"]
