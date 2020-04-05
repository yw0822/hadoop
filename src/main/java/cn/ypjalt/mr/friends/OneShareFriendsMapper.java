package cn.ypjalt.mr.friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OneShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 获取一行 A:B,C,D,F,E,O
        String line = value.toString();
        // 2  切割
        String[] fields = line.split(":");
        // 3 获取person和好友
        String person = fields[0];
        String[] friends = fields[1].split(",");
        // 4 写出去
        for (String friend:friends){
            context.write(new Text(person), new Text(friend));
        }
    }
}
