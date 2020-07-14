package ir.ac.sbu.data_mining.conf;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

import ir.ac.sbu.data_mining.exception.LoadConfigurationException;

public class Conf {
    private static Conf instance;
    private String kafkaBrokers;
    private String kafkaInputTopic;
    private String kafkaOutputTopic;
    private int queueSize;
    private int threadCount;
    private int maxPollDuration;
    private int maxPollRecords;
    private String kafkaConsumerGroupId;
    private String kafkaAutoOffsetReset;
    private String nlpUrl;
    private int serverPort;

    public static Conf load() throws LoadConfigurationException {
        if (instance == null) {
            try {
                instance = new Conf();
                InputStream inputStream = Thread
                        .currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("stream-processor.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                instance.kafkaBrokers = properties.getProperty("stream-processor.kafka.brokers");
                instance.kafkaInputTopic = properties.getProperty("stream-processor.kafka.topic.in");
                instance.kafkaOutputTopic = properties.getProperty("stream-processor.kafka.topic.out");
                instance.queueSize = Integer.parseInt(properties.getProperty("stream-processor.queue.size"));
                instance.threadCount = Integer.parseInt(properties.getProperty("stream-processor.thread.count"));
                instance.maxPollDuration = Integer.parseInt(properties.getProperty("stream-processor.kafka.max.poll.duration"));
                instance.maxPollRecords = Integer.parseInt(properties.getProperty("stream-processor.kafka.max.poll.records"));
                instance.kafkaConsumerGroupId = properties.getProperty("stream-processor.kafka.group.id");
                instance.kafkaAutoOffsetReset = properties.getProperty("stream-processor.kafka.auto.offset.reset");
                instance.nlpUrl = properties.getProperty("stream-processor.nlp.url");
                instance.serverPort = Integer.parseInt(properties.getProperty("stream-processor.server.port"));
            } catch (IOException ex) {
                throw new LoadConfigurationException(ex);
            }
        }
        return instance;
    }

    public String getKafkaInputTopic() {
        return this.kafkaInputTopic;
    }

    public int getMaxPollDuration() {
        return this.maxPollDuration;
    }

    public int getMaxPollRecords() {
        return this.maxPollRecords;
    }

    public String getKafkaBrokers() {
        return this.kafkaBrokers;
    }

    public int getQueueSize() {
        return this.queueSize;
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    public String getKafkaConsumerGroupId() {
        return this.kafkaConsumerGroupId;
    }

    public String getKafkaAutoOffsetReset() {
        return this.kafkaAutoOffsetReset;
    }

    public String getNlpUrl() {
        return this.nlpUrl;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getKafkaOutputTopic() {
        return kafkaOutputTopic;
    }
}
