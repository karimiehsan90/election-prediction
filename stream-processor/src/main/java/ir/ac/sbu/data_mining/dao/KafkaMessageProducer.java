package ir.ac.sbu.data_mining.dao;

import ir.ac.sbu.data_mining.annotation.PrometheusMetric;
import ir.ac.sbu.data_mining.conf.Conf;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaMessageProducer implements MessageProducer {
    private Conf conf;
    private KafkaProducer<String, String> kafkaProducer;
    private Logger logger = LoggerFactory.getLogger("cli");
    @PrometheusMetric(metricName = "stream_processor_proceed_count")
    private AtomicInteger sentCount = new AtomicInteger(0);

    public KafkaMessageProducer(Conf conf, KafkaProducer<String, String> kafkaProducer) {
        this.conf = conf;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void produce(String content) {
        kafkaProducer.send(new ProducerRecord<>(
                conf.getKafkaOutputTopic(),
                content
        ));
        int newCount = sentCount.incrementAndGet();
        if (newCount % 1000 == 0) {
            flush();
            logger.info("{} data wrote to kafka", newCount);
        }
    }

    @Override
    public void flush() {
        kafkaProducer.flush();
    }
}
