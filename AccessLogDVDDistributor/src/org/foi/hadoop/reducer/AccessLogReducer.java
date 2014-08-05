package org.foi.hadoop.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;;

public class AccessLogReducer extends Reducer<Text, Text, Text, Text> {
		
//		private int maxSiteVisits = 0;
//		private String maxSiteVisitsName;
		//@Override
//	    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//	        int sum = 0;
//	        for (IntWritable val : values) {
//	            sum += val.get();
//	        }
//	        
//	        context.write(key, new IntWritable(sum));
//	    }
	
		private String topLink = "";
	    private int topLinkHits = 0;
		
		@Override
	    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	        String[] tempData;
	        for (Text val : values) {
	            tempData = val.toString().split("\\t");
	            if (tempData.length == 2 && (Integer.parseInt(tempData[1]) > topLinkHits)) {
	            	topLink = tempData[0];
	            	topLinkHits = Integer.parseInt(tempData[1]);
	            }
	        }
	        context.write(key, new Text(topLink + " " + topLinkHits));
	    }
		
	}
