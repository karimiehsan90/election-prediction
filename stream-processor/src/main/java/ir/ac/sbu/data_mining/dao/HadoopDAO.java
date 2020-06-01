package ir.ac.sbu.data_mining.dao;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;

import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.data.TweetHadoopData;

public class HadoopDAO {
    private Conf conf;
    private Configuration hadoopConf;
    private FileSystem hdfs;
    private PrintWriter writer;
    private Path outFile;

    public HadoopDAO(Conf conf, Configuration hadoopConf, FileSystem hdfs, Path outFile) {
        this.conf = conf;
        this.hadoopConf = hadoopConf;
        this.hdfs = hdfs;
        this.outFile = outFile;
    }

    public void writeLine(String line) {
        writer.append(line);
    }

    public void flush() {
        writer.flush();
    }

    public void initHadoop() throws IOException {
        hadoopConf.setBoolean("dfs.support.append", true);
        if (!hdfs.exists(outFile)) {
            hdfs.create(outFile);
        }
        FSDataOutputStream fsAppend = hdfs.append(outFile);
        this.writer = new PrintWriter(fsAppend);
    }

    public String convertTweetHadoopDataToString(TweetHadoopData tweet) {
        return tweet.getFavoriteCount() + " " 
            + tweet.getRetweetCount() + " "
            + tweet.getEmotion().trim() + " "
            + tweet.getScreenName() + "\n";
    }
}
