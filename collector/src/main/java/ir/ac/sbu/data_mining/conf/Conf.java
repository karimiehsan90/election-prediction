package ir.ac.sbu.data_mining.conf;

import ir.ac.sbu.data_mining.exception.LoadConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {
    private static Conf instance;
    private String kafkaBrokers;
    private String kafkaTopic;
    private int queueSize;
    private int maxPollDuration;
    private int maxPollRecords;
    private String kafkaConsumerGroupId;
    private String kafkaAutoOffsetReset;
    private String hadoopOutPath;
    private int serverPort;

    public static Conf load() throws LoadConfigurationException {
        if (instance == null) {
            try {
                instance = new Conf();
                InputStream inputStream = Thread
                    .currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("collector.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                instance.kafkaBrokers = properties.getProperty("collector.kafka.brokers");
                instance.kafkaTopic = properties.getProperty("collector.kafka.topic");
                instance.queueSize = Integer.parseInt(properties.getProperty("collector.queue.size"));
                instance.maxPollDuration = Integer.parseInt(properties.getProperty("collector.kafka.max.poll.duration"));
                instance.maxPollRecords = Integer.parseInt(properties.getProperty("collector.kafka.max.poll.records"));
                instance.kafkaConsumerGroupId = properties.getProperty("collector.kafka.group.id");
                instance.kafkaAutoOffsetReset = properties.getProperty("collector.kafka.auto.offset.reset");
                instance.hadoopOutPath = properties.getProperty("collector.hdfs.out.path");
                instance.serverPort = Integer.parseInt(properties.getProperty("collector.server.port"));
            } catch (IOException ex) {
                throw new LoadConfigurationException(ex);
            }
        }
        return instance;
    }
    public String getKafkaTopic() {
        return this.kafkaTopic;
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

    public String getKafkaConsumerGroupId() {
        return this.kafkaConsumerGroupId;
    }

    public String getKafkaAutoOffsetReset() {
        return this.kafkaAutoOffsetReset;
    }

    public String getHadoopOutPath() {
        return this.hadoopOutPath;
    }

    public int getServerPort() {
        return serverPort;
    }
}