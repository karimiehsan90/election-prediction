package ir.ac.sbu.data_mining;

import ir.ac.sbu.data_mining.mapper.TweetMapper;
import ir.ac.sbu.data_mining.reducer.SumReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "predictor");
        job.setMapperClass(TweetMapper.class);
        job.setCombinerClass(SumReducer.class);
        job.setReducerClass(SumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setJar("predictor.jar");
        FileInputFormat.addInputPath(job, new Path("/election-prediction/collector"));
        FileOutputFormat.setOutputPath(job, new Path("/election-prediction/predictor"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
