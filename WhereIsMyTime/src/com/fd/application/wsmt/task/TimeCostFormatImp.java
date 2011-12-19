package com.fd.application.wsmt.task;

public class TimeCostFormatImp implements TimeCostFormat {

	@Override
	public String format(long minSeconds) {
		int seconds = (int) (minSeconds/1000);
		int hours = seconds/3600;
		seconds = seconds%3600;
		int minutes = seconds/60;
		seconds = seconds%3600;
		String timeCost = String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);
		return timeCost;
	}

}
