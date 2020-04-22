package ir.ac.sbu.data_mining.dao;

public interface MessageProducer {
    void produce(String content);
    void flush();
}
