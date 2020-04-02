package ir.ac.sbu.data_mining.service;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.dao.MessageProducer;
import ir.ac.sbu.data_mining.dao.KafkaProducerMock;
import java.util.Scanner;
import org.junit.Test;
import org.junit.BeforeClass;

public class FileReaderTest {
    private static FileReader fileReader;
    @BeforeClass
    public static void init() throws Exception {
        Conf conf = Conf.load();
        MessageProducer producer = new KafkaProducerMock(conf);
        Scanner scanner = new Scanner(Thread
            .currentThread()
            .getContextClassLoader()
            .getResourceAsStream("test-tweets.json"));
        fileReader = new FileReader(conf, producer, scanner);
    }

    @Test
    public void testRead() {
        fileReader.read();
    }
}
