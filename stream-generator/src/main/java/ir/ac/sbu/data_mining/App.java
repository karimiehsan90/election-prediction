package ir.ac.sbu.data_mining;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.dao.MessageProducer;
import ir.ac.sbu.data_mining.dao.KafkaMessageProducer;
import ir.ac.sbu.data_mining.service.Reader;
import ir.ac.sbu.data_mining.service.FileReader;
import ir.ac.sbu.data_mining.factory.KafkaProducerFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import java.io.FileInputStream;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws Exception {
        Conf conf = Conf.load();
        KafkaProducer<String, String> kafkaProducer = KafkaProducerFactory.createKafkaProducer(conf);
        Scanner scanner = new Scanner(new FileInputStream(conf.getInputPath()));
        MessageProducer messageProducer = new KafkaMessageProducer(conf, kafkaProducer);
        Reader reader = new FileReader(conf, messageProducer, scanner);
        reader.read();
    }
}
