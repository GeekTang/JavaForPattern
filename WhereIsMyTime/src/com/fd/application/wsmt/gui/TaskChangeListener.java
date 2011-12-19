package com.fd.application.wsmt.gui;

import java.util.List;

import org.eclipse.swt.events.SelectionListener;

import com.fd.application.wsmt.task.Task;
import com.fd.application.wsmt.task.UpdateValue;

public interface TaskChangeListener extends SelectionListener {
	
	void setValues(List<UpdateValue> values);
	
	void setTasks(List<Task> tasks);
	
	void setDynamicalLable(DynamicalLabel dynamicalLable);

}
