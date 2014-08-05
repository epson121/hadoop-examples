package org.foi.hadoop.driver;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.foi.hadoop.mapper.SalesSumPerCategoryMapper;
import org.foi.hadoop.mapper.SalesSumPerCityMapper;
import org.foi.hadoop.reducer.SalesSumPerCategoryReducer;
import org.foi.hadoop.reducer.SalesSumPerCityReducer;

public class SalesSumPerCategoryDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        
        Job job = new Job(conf);
        job.setJarByClass(SalesSumPerCategoryDriver.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        job.setMapperClass(SalesSumPerCategoryMapper.class);
        job.setCombinerClass(SalesSumPerCategoryReducer.class);
        job.setReducerClass(SalesSumPerCategoryReducer.class);
        
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new SalesSumPerCategoryDriver(), args);
        System.exit(res);
    }
	
}
