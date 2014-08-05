package org.foi.hadoop.reducer;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;;

public class HighestIndividualSaleCityReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	  @Override
	    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
	        double highest = 0.0;
	        double temp;
	        for (DoubleWritable val : values) {
	            temp = val.get();
	            if (temp > highest) {
	            	highest = temp;
	            }
	        }
	        context.write(key, new DoubleWritable(highest));
	    }
	}
