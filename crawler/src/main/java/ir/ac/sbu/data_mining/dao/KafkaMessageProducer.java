package ir.ac.sbu.data_mining.dao;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.entity.Tweet;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class KafkaMessageProducer {
    private Logger logger = LoggerFactory.getLogger("cli");
    private Conf conf;
    private KafkaProducer<String, Tweet> kafkaProducer;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public KafkaMessageProducer(Conf conf, KafkaProducer<String, Tweet> kafkaProducer) {
        this.conf = conf;
        this.kafkaProducer = kafkaProducer;
    }

    public void produce(Tweet data) {
        kafkaProducer.send(new ProducerRecord<>(conf.getKafkaTweetsTopic(), data));
        int count = atomicInteger.incrementAndGet();
        if (count % 1000 == 0) {
            flush();
            logger.info("{} tweets wrote to kafka", count);
        }
    }

    private void flush() {
        kafkaProducer.flush();
    }
}
