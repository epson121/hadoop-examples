package org.foi.hadoop.mapper;

import java.io.IOException;
import java.util.StringTokenizer;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class SalesSumPerCityMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private Text even = new Text("even");
    private Text odd = new Text("odd");
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] data = line.split("\\t");
        Text city = new Text(data[2]);
        context.write(city, one);
    }
}
