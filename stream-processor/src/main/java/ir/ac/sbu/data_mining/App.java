package ir.ac.sbu.data_mining;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.dao.KafkaMessageProducer;
import ir.ac.sbu.data_mining.dao.MessageProducer;
import ir.ac.sbu.data_mining.factory.KafkaConsumerFactory;
import ir.ac.sbu.data_mining.factory.KafkaProducerFactory;
import ir.ac.sbu.data_mining.factory.RunnableFactory;
import ir.ac.sbu.data_mining.service.NLPConnector;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {
    public static void main( String[] args ) throws Exception {
        Conf conf = Conf.load();
        KafkaConsumer<String, String> kafkaConsumer = KafkaConsumerFactory.createKafkaConsumer(conf);
        KafkaProducer<String, String> kafkaProducer = KafkaProducerFactory.createKafkaProducer(conf);
        MessageProducer producer = new KafkaMessageProducer(conf, kafkaProducer);
        BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(conf.getQueueSize());
        AtomicBoolean closed = new AtomicBoolean(false);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();
        NLPConnector connector = new NLPConnector(conf, objectWriter);
        List<Runnable> runnables = RunnableFactory.createRunnables(conf
            , kafkaConsumer
            , messageQueue
            , closed
            , objectMapper
            , connector
            , producer);

        for (Runnable runnable : runnables) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
