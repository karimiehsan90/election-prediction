package ir.ac.sbu.data_mining.dao;

public interface MessageConsumer extends Runnable {
    void consume() throws InterruptedException;
}
