package ir.ac.sbu.data_mining.dao;

import ir.ac.sbu.data_mining.conf.Conf;
import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaHadoopDataConsumer implements MessageConsumer, AutoCloseable {
    private Logger logger = LoggerFactory.getLogger("cli");
    private Conf conf;
    private BlockingQueue<String> hadoopDatas;
    private AtomicBoolean closed;
    private KafkaConsumer<String, String> consumer;

    public KafkaHadoopDataConsumer(Conf conf, BlockingQueue<String> hadoopDatas
        , KafkaConsumer<String, String> consumer
        , AtomicBoolean closed) {
        this.conf = conf;
        this.hadoopDatas = hadoopDatas;
        this.consumer = consumer;
        this.closed = closed;
    }

    @Override
    public void close() {
        closed.set(true);
    }

    @Override
    public void consume() throws InterruptedException {
        ConsumerRecords<String, String> records = consumer
            .poll(Duration.ofMillis(conf.getMaxPollDuration()));
        for (ConsumerRecord<String, String> record : records) {
            hadoopDatas.put(record.value());
        }
        try {
            if (records.count() > 0) {
                consumer.commitSync();
            }
        } catch (TimeoutException | CommitFailedException e) {
            logger.warn("Unable to commit changes for {} consumer", conf.getKafkaTopic());
        } catch (org.apache.kafka.common.errors.InterruptException e) {
            logger.warn("Unable to commit changes for {} consumer because of interruption"
                , conf.getKafkaTopic());
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                consume();
            }
        } catch (InterruptedException e) {
            logger.info("Consumer service stopped successfully");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("Consumer service stopped with failures");
        }
    }
}
