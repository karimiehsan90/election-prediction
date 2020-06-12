package ir.ac.sbu.data_mining.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.PrintWriter;

public class HadoopDAO {
    private Configuration hadoopConf;
    private FileSystem hdfs;
    private PrintWriter writer;
    private Path outFile;

    public HadoopDAO(Configuration hadoopConf, FileSystem hdfs, Path outFile) {
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
}
