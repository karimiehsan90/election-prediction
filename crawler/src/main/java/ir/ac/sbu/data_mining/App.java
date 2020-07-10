package ir.ac.sbu.data_mining;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.controller.MetricsController;
import ir.ac.sbu.data_mining.dao.KafkaMessageProducer;
import ir.ac.sbu.data_mining.dao.RedisDao;
import ir.ac.sbu.data_mining.entity.Tweet;
import ir.ac.sbu.data_mining.factory.KafkaProducerFactory;
import ir.ac.sbu.data_mining.service.CrawlerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import redis.clients.jedis.Jedis;
import spark.Spark;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Conf conf = Conf.load();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();
        Jedis jedis = new Jedis(conf.getRedisHost(), conf.getRedisPort());

        KafkaProducer<String, Tweet> kafkaTweetsProducer = KafkaProducerFactory.createKafkaProducer(conf);
        RedisDao redisDao = new RedisDao(jedis);

        KafkaMessageProducer tweetKafkaMessageProducer = new KafkaMessageProducer(conf, kafkaTweetsProducer);

        CrawlerService crawlerService = new CrawlerService(twitter,
                redisDao,
                tweetKafkaMessageProducer,
                conf);
        Thread crawlerThread = new Thread(crawlerService);
        crawlerThread.start();

        MetricsController controller = new MetricsController();
        Spark.port(conf.getServerPort());

        Spark.get("/metrics", ((request, response) -> controller.process(crawlerService, controller)));
        Spark.after((request, response) -> {
            response.header("Content-Type", "text/plain");
            controller.increaseRequestCount();
        });
    }
}
