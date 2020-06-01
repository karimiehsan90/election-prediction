package ir.ac.sbu.data_mining;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.dao.HadoopDAO;
import ir.ac.sbu.data_mining.factory.KafkaConsumerFactory;
import ir.ac.sbu.data_mining.factory.RunnableFactory;
import ir.ac.sbu.data_mining.service.NLPConnector;

public class App {
    public static void main( String[] args ) throws Exception {
        Conf conf = Conf.load();
        KafkaConsumer<String, String> kafkaConsumer = KafkaConsumerFactory.createKafkaConsumer(conf);
        BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(conf.getQueueSize());
        AtomicBoolean closed = new AtomicBoolean(false);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();
        NLPConnector connector = new NLPConnector(conf, objectWriter);
        Configuration hadoopConf = new Configuration();
        FileSystem hdfs = FileSystem.get(hadoopConf);
        Path outFile = new Path(conf.getHadoopOutPath());
        HadoopDAO hadoopDAO = new HadoopDAO(conf, hadoopConf, hdfs, outFile);
        BlockingQueue<String> hadoopDatas = new ArrayBlockingQueue<>(conf.getQueueSize());
        List<Runnable> runnables = RunnableFactory.createRunnables(conf
            , kafkaConsumer
            , messageQueue
            , closed
            , objectMapper
            , connector
            , hadoopDAO
            , hadoopDatas);

        for (Runnable runnable : runnables) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
