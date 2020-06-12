package ir.ac.sbu.data_mining.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.dao.KafkaMessageConsumer;
import ir.ac.sbu.data_mining.dao.MessageConsumer;
import ir.ac.sbu.data_mining.dao.MessageProducer;
import ir.ac.sbu.data_mining.service.MessageProcessor;
import ir.ac.sbu.data_mining.service.NLPConnector;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableFactory {
    public static List<Runnable> createRunnables(Conf conf
        , KafkaConsumer<String, String> kafkaConsumer
        , BlockingQueue<String> messageQueue
        , AtomicBoolean closed
        , ObjectMapper objectMapper
        , NLPConnector connector
        , MessageProducer producer) {
        List<Runnable> runnables = new ArrayList<>(conf.getThreadCount() + 1);
        MessageConsumer messageConsumer = new KafkaMessageConsumer(conf
            , messageQueue
            , kafkaConsumer
            , closed);
        runnables.add(messageConsumer);
        for (int i = 0 ; i < conf.getThreadCount() ; i++) {
            MessageProcessor processor = new MessageProcessor(conf
                , messageQueue
                , closed
                , objectMapper
                , connector
                , producer);
            runnables.add(processor);
        }
        return runnables;
    }
}
