package ir.ac.sbu.data_mining.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.dao.HadoopDAO;
import ir.ac.sbu.data_mining.data.TweetHadoopData;
import ir.ac.sbu.data_mining.entity.Tweet;
import ir.ac.sbu.data_mining.request.TweetRequest;

public class MessageProcessor implements Runnable {
    private Logger logger = LoggerFactory.getLogger("cli");
    private Conf conf;
    private BlockingQueue<String> messageQueue;
    private AtomicBoolean closed;
    private ObjectMapper objectMapper;
    private NLPConnector connector;
    private HadoopDAO hadoopDAO;
    private BlockingQueue<String> hadoopDatas;
    private int count = 0;

    public MessageProcessor(Conf conf
        , BlockingQueue<String> messageQueue
        , AtomicBoolean closed
        , ObjectMapper objectMapper
        , NLPConnector connector
        , HadoopDAO hadoopDAO
        , BlockingQueue<String> hadoopDatas) {
        this.conf = conf;
        this.messageQueue = messageQueue;
        this.closed = closed;
        this.objectMapper = objectMapper;
        this.connector = connector;
        this.hadoopDAO = hadoopDAO;
        this.hadoopDatas = hadoopDatas;
    }

    @Override
    public void run() {
        while (!closed.get()) {
            try {
                count++;
                String message = messageQueue.take();
                process(message);
                if (count % 1000 == 0) {
                    logger.info("{} tweets processed successfully", count);
                }
            } catch (InterruptedException ex) {

            }
        }
    }

    public void process(String message) {
        try {
            Tweet tweet = objectMapper.readValue(message, Tweet.class);
            TweetRequest request = convertTweetToRequest(tweet);
            String emotion = connector.getEmotion(request);
            TweetHadoopData hadoopData = convertTweetToTweetHadoopData(tweet, emotion);
            String line = hadoopDAO.convertTweetHadoopDataToString(hadoopData);
            hadoopDatas.put(line);
        } catch (Exception ex) {
            logger.warn("tweet cannot process", ex);
            logger.warn(message);
        }
    }

    private TweetHadoopData convertTweetToTweetHadoopData(Tweet tweet, String emotion) {
        return new TweetHadoopData(tweet.getFavoriteCount()
            , tweet.getRetweetCount()
            , emotion
            , tweet.getScreenName());
    }

    private TweetRequest convertTweetToRequest(Tweet tweet) {
        List<String> hashtags = tweet.getEntities().getHashtags().stream()
            .map(h -> h.getText()).collect(Collectors.toList());
        if (tweet.getExtendedEntities() != null
            && tweet.getExtendedEntities().getHashtags() != null) {
            hashtags.addAll(
                tweet.getExtendedEntities().getHashtags().stream()
                    .map(h -> h.getText()).collect(Collectors.toList())
            );
        }
        int favoriteCount = tweet.getFavoriteCount();
        int retweetCount = tweet.getRetweetCount();
        String screenName = tweet.getScreenName();
        String text = tweet.getText();
        return new TweetRequest(hashtags, favoriteCount, retweetCount, screenName, text);
    }
}
