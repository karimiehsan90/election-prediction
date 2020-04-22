package ir.ac.sbu.data_mining.service;

import ir.ac.sbu.data_mining.dao.MessageProducer;
import ir.ac.sbu.data_mining.conf.Conf;
import java.util.Scanner;

public class FileReader implements Reader {
    private Conf conf;
    private MessageProducer producer;
    private Scanner scanner;

    public FileReader(Conf conf, MessageProducer producer, Scanner scanner) {
        this.conf = conf;
        this.producer = producer;
        this.scanner = scanner;
    }

    @Override
    public void read() {
        while (scanner.hasNextLine()) {
            producer.produce(scanner.nextLine());
        }
        producer.flush();
    }
}