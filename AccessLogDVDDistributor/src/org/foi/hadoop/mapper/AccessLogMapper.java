package org.foi.hadoop.mapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class AccessLogMapper extends Mapper<LongWritable, Text, Text, Text> {
	
    private String splitRegex = "(.*\\..*\\..*\\..*)\\s(.*|-)\\s(.*|-)\\s(\\[.*\\])\\s(\\\".*\\\")\\s(.*)\\s(.*)";
	private Matcher m;
	private Pattern p = Pattern.compile(splitRegex);
	private Text request = new Text("assets/js/the-associates.js");
	private Text mostHits = new Text("Most hits");
	private final static IntWritable one = new IntWritable(1);
	
	// counts th enumber of times this url was accessed
//    @Override
//    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//    	String line = value.toString();
//		//if (line.contains("assets/js/the-associates.js")) {
//    	if (line.contains("10.99.99.186")) {
//    		context.write(request, one);
//    	}
//    }
	
	// count overall number of accesses (ie. for each site
//	 @Override
//	    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//	    	String line = value.toString();
//			String[] lineElements = line.split(" ");
//			for (String string : lineElements) {
//				String site = lineElements[6].replace("http://www.the-associates.co.uk", "");
//				Text siteName = new Text(site);
//		    	context.write(mostHits, one);
//			}
//	 	}
	
	@Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	String line = value.toString();
    	context.write(mostHits, new Text(line));
 	}
}
