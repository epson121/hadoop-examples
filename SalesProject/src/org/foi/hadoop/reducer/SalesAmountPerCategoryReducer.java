package org.foi.hadoop.reducer;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;;

public class SalesAmountPerCategoryReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	  @Override
	    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
	        double sum = 0.0;
	        for (DoubleWritable val : values) {
	            sum += val.get();
	        }
	        // round the number hack
	        sum = Math.round(sum * 100);
	        sum = sum/100;
	        context.write(key, new DoubleWritable(sum));
	    }
	}
