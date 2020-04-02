package ir.ac.sbu.data_mining.conf;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import ir.ac.sbu.data_mining.exception.LoadConfigurationException;

public class Conf {
    private static Conf instance;
    private String inputPath;
    private String kafkaBrokers;
    private String kafkaTopic;
    private int queueSize;

    public static Conf load() throws LoadConfigurationException {
        if (instance == null) {
            try {
                instance = new Conf();
                InputStream inputStream = Thread
                    .currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("stream-generator.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                instance.inputPath = properties.getProperty("stream-generator.input.path");
                instance.kafkaBrokers = properties.getProperty("stream-generator.kafka.brokers");
                instance.kafkaTopic = properties.getProperty("stream-generator.kafka.topic");
                instance.queueSize = Integer.parseInt(properties.getProperty("stream-generator.queue.size"));
            } catch (IOException ex) {
                throw new LoadConfigurationException(ex);
            }
        }
        return instance;
    }

    public String getInputPath() {
        return this.inputPath;
    }

    public String getKafkaBrokers() {
        return this.kafkaBrokers;
    }

    public String getKafkaTopic() {
        return this.kafkaTopic;
    }

    public int getQueueSize() {
        return this.queueSize;
    }
}
