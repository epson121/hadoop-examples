package org.foi.hadoop.mapper;

import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class EvenOddSumMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private Text even = new Text("even");
    private Text odd = new Text("odd");
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	int number  = Integer.parseInt(value.toString());
        IntWritable n = new IntWritable(number);
        if (number % 2 == 0) {
        	context.write(even, n);
        } else {
        	context.write(odd, n);
        }
    }
}
