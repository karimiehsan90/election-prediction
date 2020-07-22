package ir.ac.sbu.data_mining.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TweetMapper extends Mapper<Object, Text, Text, IntWritable> {
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] valArr = value.toString().split(" ");
        String emotion = valArr[2];
        String political = valArr[3];
        int rate = context.getConfiguration().getInt(emotion, 0);
        context.write(new Text(political), new IntWritable(rate));
    }
}
