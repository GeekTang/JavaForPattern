package com.fd.application.wsmt.gui;

import com.fd.application.wsmt.task.UpdateValue;

public interface DynamicalLabel extends Runnable {
	
	UpdateValue getTheValue();

	void setTheValue(UpdateValue theValue);
	
	void startUpadte();
	
	void stopUpdate();

}