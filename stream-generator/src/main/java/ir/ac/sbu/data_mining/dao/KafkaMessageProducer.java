package ir.ac.sbu.data_mining.dao;

import ir.ac.sbu.data_mining.conf.Conf;
import java.util.List;
import java.util.concurrent.Future;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaMessageProducer implements MessageProducer {
    private Conf conf;
    private KafkaProducer<String, String> kafkaProducer;
    private List<String> messages = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger("cli");

    public KafkaMessageProducer(Conf conf, KafkaProducer<String, String> kafkaProducer) {
        this.conf = conf;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void produce(String content) {
        messages.add(content);
        if (messages.size() == conf.getQueueSize()) {
            flush();
        }
    }

    @Override
    public void flush() {
        for (String message : messages) {
            kafkaProducer.send(new ProducerRecord<>(
                conf.getKafkaTopic(),
                message
            ));
        }
        kafkaProducer.flush();
        logger.info("{} tweets added to kafka", messages.size());
        messages = new ArrayList<>();
    }
}
