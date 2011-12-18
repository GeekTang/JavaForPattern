package com.fd.application.wsmt;

public interface DynamicalLabel extends Runnable {
	
	UpdateValue getTheValue();

	void setTheValue(UpdateValue theValue);
	
	void startUpadte();
	
	void stopUpdate();

}