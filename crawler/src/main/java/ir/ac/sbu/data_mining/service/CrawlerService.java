package ir.ac.sbu.data_mining.service;

import ir.ac.sbu.data_mining.annotations.PrometheusMetric;
import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.dao.KafkaMessageProducer;
import ir.ac.sbu.data_mining.dao.RedisDao;
import ir.ac.sbu.data_mining.entity.Tweet;
import twitter4j.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CrawlerService implements Runnable {
    private Twitter twitter;
    private RedisDao redisDao;
    private KafkaMessageProducer tweetProducer;
    private Conf conf;
    @PrometheusMetric(metricName = "crawler_seen_tweets")
    private AtomicInteger seenTweets = new AtomicInteger(0);
    @PrometheusMetric(metricName = "crawler_not_english_language_tweets")
    private AtomicInteger notEnglishLanguageTweets = new AtomicInteger(0);
    @PrometheusMetric(metricName = "crawler_tweets_cache_hit")
    private AtomicInteger tweetsCacheHits = new AtomicInteger(0);

    public CrawlerService(Twitter twitter,
                          RedisDao redisDao,
                          KafkaMessageProducer tweetProducer,
                          Conf conf) {
        this.twitter = twitter;
        this.redisDao = redisDao;
        this.tweetProducer = tweetProducer;
        this.conf = conf;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (String hashtag : conf.getCandidate()) {
                    Query query = new Query();
                    query.setQuery(hashtag);
                    query.setLang("en");
                    QueryResult result = twitter.search(query);
                    List<Status> tweets = result.getTweets();
                    for (Status tweet : tweets) {
                        seenTweets.incrementAndGet();
                        if (!redisDao.contains(String.valueOf(tweet.getId()))) {
                            if (tweet.getLang().equals("en")) {
                                redisDao.add(String.valueOf(tweet.getId()));
                                tweetProducer.produce(new Tweet(tweet.getFavoriteCount(),
                                        tweet.getRetweetCount(),
                                        conf.getCandidate().get(0),
                                        tweet.getText()));
                            } else {
                                notEnglishLanguageTweets.incrementAndGet();
                            }
                        } else {
                            tweetsCacheHits.incrementAndGet();
                        }
                    }
                    Thread.sleep(conf.getRequestInterval());
                }
            } catch (TwitterException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
