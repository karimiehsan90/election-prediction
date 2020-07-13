package ir.ac.sbu.data_mining.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Conf {
    private static Conf instance;
    private String kafkaTweetsTopic;
    private String kafkaBrokers;
    private String redisHost;
    private int redisPort;
    private int requestInterval;
    private int serverPort;
    private List<String> candidate;

    public static Conf load() throws IOException {
        if (instance == null) {
            instance = new Conf();
            Properties properties = new Properties();
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("crawler.properties");
            properties.load(inputStream);
            instance.kafkaTweetsTopic = properties.getProperty("crawler.kafka.topic.tweets");
            instance.kafkaBrokers = properties.getProperty("crawler.kafka.brokers");
            instance.redisHost = properties.getProperty("crawler.redis.host");
            instance.redisPort = Integer.parseInt(properties.getProperty("crawler.redis.port"));
            instance.requestInterval = Integer.parseInt(properties.getProperty("crawler.request.interval"));
            instance.serverPort = Integer.parseInt(properties.getProperty("crawler.server.port"));
            String candidate = properties.getProperty("crawler.candidate");
            String[] candidateTags = candidate.split(",");
            instance.candidate = Arrays.asList(candidateTags);
        }
        return instance;
    }

    public String getKafkaTweetsTopic() {
        return kafkaTweetsTopic;
    }

    public String getKafkaBrokers() {
        return kafkaBrokers;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public int getRequestInterval() {
        return requestInterval;
    }

    public int getServerPort() {
        return serverPort;
    }

    public List<String> getCandidate() {
        return candidate;
    }
}
