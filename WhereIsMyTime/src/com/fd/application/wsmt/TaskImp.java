package com.fd.application.wsmt;

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

	@Override
	public void start() {
		
		startTime = new Date();

	}

	@Override
	public void stop() {
		
		Date currentDate = new Date();
		timecost.setTimeCost(timecost.getTimeCost() + currentDate.getTime() - startTime.getTime());
		
	}

	@Override
	public String getTaskName() {
		return taskName;
	}

	@Override
	public String getTimeCost() {
		return timecost.toString();
	}

	@Override
	public String getValue() {
		stop();
		start();
		return timecost.toString();
	}

}
