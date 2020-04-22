package ir.ac.sbu.data_mining.dao;

import ir.ac.sbu.data_mining.conf.Conf;
import static org.junit.Assert.*;

public class KafkaProducerMock implements MessageProducer {
    private Conf conf;
    private int count;
    private int flushTimes;

    public KafkaProducerMock(Conf conf) {
        this.conf = conf;
    }

    @Override
    public void produce(String content) {
        count++;
        if (count == conf.getQueueSize()) {
            flush();
        }
    }

    @Override
    public void flush() {
        if (flushTimes == 0) {
            assertEquals(20, count);
        }
        else if (flushTimes == 1) {
            assertEquals(5, count);
        }
        else {
            fail(flushTimes + " is flush times and is wrong");
        }
        count = 0;
        flushTimes++;
    }
}
