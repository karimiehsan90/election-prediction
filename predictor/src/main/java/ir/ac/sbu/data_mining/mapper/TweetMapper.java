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
        int rate;
        switch (emotion) {
            case  "joy": rate = 10; break;
            case  "anger": rate = -4; break;
            case  "fear": rate = -2; break;
            case  "sadness": rate = -1; break;
            default: rate = 0;
        }
        context.write(new Text(political), new IntWritable(rate));
    }
}
