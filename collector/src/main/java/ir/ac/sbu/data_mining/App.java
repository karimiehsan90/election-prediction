package ir.ac.sbu.data_mining;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.controller.MetricsController;
import ir.ac.sbu.data_mining.dao.HadoopDAO;
import ir.ac.sbu.data_mining.dao.KafkaHadoopDataConsumer;
import ir.ac.sbu.data_mining.dao.MessageConsumer;
import ir.ac.sbu.data_mining.exception.LoadConfigurationException;
import ir.ac.sbu.data_mining.factory.KafkaConsumerFactory;
import ir.ac.sbu.data_mining.service.HadoopService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import spark.Spark;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {
    public static void main(String[] args) throws IOException, LoadConfigurationException {
        Conf conf = Conf.load();
        Configuration hadoopConf = new Configuration();
        FileSystem hdfs = FileSystem.get(hadoopConf);
        Path outFile = new Path(conf.getHadoopOutPath());
        BlockingQueue<String> hadoopDatas = new ArrayBlockingQueue<>(conf.getQueueSize());
        AtomicBoolean closed = new AtomicBoolean(false);
        HadoopDAO dao = new HadoopDAO(hadoopConf, hdfs, outFile);
        HadoopService service = new HadoopService(hadoopDatas, closed, dao);
        Thread hadoopServiceThread = new Thread(service);
        KafkaConsumer<String, String> kafkaConsumer = KafkaConsumerFactory.createKafkaConsumer(conf);
        MessageConsumer consumer = new KafkaHadoopDataConsumer(conf, hadoopDatas, kafkaConsumer, closed);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        hadoopServiceThread.start();
        MetricsController controller = new MetricsController();
        Spark.port(conf.getServerPort());
        Spark.get("/metrics", (request, response) -> controller.process(service));
        Spark.after("/*", (request, response) ->
            response.header("Content-Type", "text/plain"));
    }
}
