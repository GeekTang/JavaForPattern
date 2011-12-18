package com.fd.application.wsmt;

import java.util.List;

import org.eclipse.swt.events.SelectionListener;

public interface TaskChangeListener extends SelectionListener {
	
	void setValues(List<UpdateValue> values);
	
	void setTasks(List<Task> tasks);
	
	void setDynamicalLable(DynamicalLabel dynamicalLable);

}
