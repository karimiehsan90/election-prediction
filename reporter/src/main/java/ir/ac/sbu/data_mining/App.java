package ir.ac.sbu.data_mining;

import java.io.IOException;

import ir.ac.sbu.data_mining.mapper.TweetMapper;
import ir.ac.sbu.data_mining.reducer.CountReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class App {
    public static void main( String[] args ) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "reporter");
        job.setMapperClass(TweetMapper.class);
        job.setCombinerClass(CountReducer.class);
        job.setReducerClass(CountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setJar("reporter.jar");
        FileInputFormat.addInputPath(job, new Path("/election-prediction/collector"));
        FileOutputFormat.setOutputPath(job, new Path("/election-prediction/reporter"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
