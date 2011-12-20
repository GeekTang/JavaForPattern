package com.fd.application.wsmt.task;

import java.util.Date;

public class TaskImp implements TaskInformation, Task, UpdateValue{
	
	private TimeCost timecost;
	
	private String taskName;
	
	private Date startTime;
	
	public TaskImp(String name, TimeCostFormat timeFormat)
	{
		taskName = name;
		timecost = new TimeCost(timeFormat);
		timecost.setTimeCost(0);
	}

	public void start() {
		
		startTime = new Date();

	}

	public void stop() {
		
		Date currentDate = new Date();
		timecost.setTimeCost(timecost.getTimeCost() + currentDate.getTime() - startTime.getTime());
		
	}

	public String getTaskName() {
		return taskName;
	}

	public String getTimeCost() {
		return timecost.toString();
	}

	public String getValue() {
		stop();
		start();
		return timecost.toString();
	}

}
