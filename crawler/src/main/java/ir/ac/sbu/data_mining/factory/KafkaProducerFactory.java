package ir.ac.sbu.data_mining.factory;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.entity.Tweet;
import ir.ac.sbu.data_mining.serializer.TweetSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerFactory {
    public static KafkaProducer<String, Tweet> createKafkaProducer(Conf conf) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, conf.getKafkaBrokers());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TweetSerializer.class.getName());
        return new KafkaProducer<>(properties);
    }
}
