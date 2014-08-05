package org.foi.hadoop.driver;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.foi.hadoop.mapper.SalesAmountPerCategoryMapper;
import org.foi.hadoop.mapper.SalesAmountPerCityMapper;
import org.foi.hadoop.reducer.SalesAmountPerCategoryReducer;
import org.foi.hadoop.reducer.SalesAmountPerCityReducer;


public class SalesAmountPerCategoryDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        
        Job job = new Job(conf);
        job.setJarByClass(SalesAmountPerCategoryDriver.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        
        job.setMapperClass(SalesAmountPerCategoryMapper.class);
        job.setCombinerClass(SalesAmountPerCategoryReducer.class);
        job.setReducerClass(SalesAmountPerCategoryReducer.class);
        
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new SalesAmountPerCategoryDriver(), args);
        System.exit(res);
    }
	
}
