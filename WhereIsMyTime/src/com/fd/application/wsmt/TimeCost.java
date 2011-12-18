package com.fd.application.wsmt;

public class TimeCost {
	
	private TimeCostFormat timeCostFormat; 

	private long timeCost;
	
	public TimeCost(TimeCostFormat theTimeCostFormat)
	{
		timeCostFormat = theTimeCostFormat;
	}

	public long getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(long timeCost) {
		this.timeCost = timeCost;
	}
	
	public String toString()
	{
		return timeCostFormat.format(timeCost);
	}
	
}
