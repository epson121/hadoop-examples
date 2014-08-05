package org.foi.hadoop.mapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class SalesAmountPerCityMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
	
	// The key class of a mapper that maps text files is always LongWritable. 
	// That is because it contains the byte offset of the current line and this 
	// could easily overflow an integer.
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] data = line.split("\\t");
        // sales amount per city
        //Text city = new Text(data[2]);
        // sales amount overall
        Text overall = new Text("overall");
        DoubleWritable amount = new DoubleWritable(Double.parseDouble(data[4]));
        //context.write(city, amount);
        context.write(overall, amount);
    }
}
