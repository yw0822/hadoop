package cn.ypjalt.mr.index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class OneIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    String name;
    Text k = new Text();
    IntWritable v = new IntWritable(1);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 获取文件名称
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        name = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // atigu ddh
        // 1获取一行
        String line = value.toString();
        // 2切割
        String[] fields = line.split(" ");
        // 3写出
        for (String word : fields
        ) {
            k.set(word + "--" + name);
            context.write(k, v);
        }
    }
}
