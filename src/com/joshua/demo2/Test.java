package com.joshua.demo2;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class Test {
	public void go() throws Exception {
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();

		// job 1 将每隔20秒执行一次
		JobDetail job = newJob(MyJob1.class).withIdentity("job1", "goup1").build();
		CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(cronSchedule("0/20 * * * * ?")).build();
		Date date = scheduler.scheduleJob(job, trigger);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out
				.println(job.getKey() + "已被安排于:" + format.format(date) + "并以如下规则重复执行" + trigger.getExpressionSummary());

		// job 2 将每2分钟执行一次（在该分钟的第15秒)
		job = newJob(MyJob2.class).withIdentity("job2", "goup1").build();
		trigger = newTrigger().withIdentity("trigger2", "group1").withSchedule(cronSchedule("15 0/2 * * * ?")).build();
		date = scheduler.scheduleJob(job, trigger);
		System.out
				.println(job.getKey() + "已被安排于:" + format.format(date) + "并以如下规则重复执行" + trigger.getExpressionSummary());

		scheduler.start();

		try {
			// 当前线程等待120秒
			Thread.sleep(120L * 1000L);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// 调度器停止运行
		scheduler.shutdown(true);
	}

	public static void main(String[] args) throws Exception {
		Test test = new Test();
		test.go();
	}

}
