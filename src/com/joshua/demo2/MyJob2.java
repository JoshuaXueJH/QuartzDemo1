package com.joshua.demo2;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob2 implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println("THIS IS JOB2-----------------" + date.format(new Date()));
	}

}
