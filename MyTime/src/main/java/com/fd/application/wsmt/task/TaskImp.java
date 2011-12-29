package com.fd.application.wsmt.task;

import java.util.Date;

import com.fd.application.wsmt.persistence.Data;

public class TaskImp implements TaskInformation, Task, UpdateValue{
	
	private TimeCost timecost;
	
	private String taskName;
	
	private Date startTime;
	
	private Data myData;
	
	public TaskImp(String name, TimeCostFormat timeFormat, int lasttimecost, Data data)
	{
		taskName = name;
		timecost = new TimeCost(timeFormat);
		timecost.setTimeCost(lasttimecost*1000);
		myData = data;
	}

	public void start() {
		
		startTime = new Date();

	}

	public void stop() {
		
		Date currentDate = new Date();
		timecost.setTimeCost(timecost.getTimeCost() + currentDate.getTime() - startTime.getTime());
		if(myData != null)
		{
			myData.record(taskName, String.valueOf((int)(timecost.getTimeCost()/1000)));
		}
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
