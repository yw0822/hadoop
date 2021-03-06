package cn.ypjalt.mr.index;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TwoIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1获取一行
        String line = value.toString();
        // 2切割
        String[] fields = line.split("--");
        // 3封装
        k.set(fields[0]);
        v.set(fields[1]);

        context.write(k, v);
    }
}
