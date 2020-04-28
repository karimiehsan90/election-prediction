package ir.ac.sbu.data_mining.mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TweetMapper extends Mapper<Object, Text, Text, IntWritable> {
    private Text word = new Text();
    private IntWritable one = new IntWritable(1);
    @Override
    public void map(Object key, Text value, Context context) 
        throws IOException, InterruptedException {
        String[] valueArr = value.toString().split(" ");
        word.set("emotion: " + valueArr[2]);
        context.write(word, one);
        word.set("political: " + valueArr[3]);
        context.write(word, one);
    }
}
