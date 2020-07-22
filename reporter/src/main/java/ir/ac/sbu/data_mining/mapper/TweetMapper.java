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
        //--- new added
        word.set("emotion score: " + valueArr[2]);
        int retweetCount = Integer.parseInt(valueArr[1]);
        int rate = context.getConfiguration().getInt(valueArr[2], 0);
        int threshold = context.getConfiguration().getInt("threshold", 0);
        if (threshold>0){
            int coefficient = Math.round(retweetCount/threshold) +1;
            rate = rate * coefficient ;
        }
        context.write(word, new IntWritable(rate));
        //--- end added

        word.set("emotion count: " + valueArr[2]);
        context.write(word, one);
        word.set("political count: " + valueArr[3]);
        context.write(word, one);
    }
}
