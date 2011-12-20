package com.fd.application.wsmt.gui;

import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

import com.fd.application.wsmt.task.Task;
import com.fd.application.wsmt.task.UpdateValue;

public class TaskChangeListenerImp implements TaskChangeListener {
	
	private List<UpdateValue> theValues;
	
	private List<Task> theTasks;
	
	private Task currentTask;
	
	private DynamicalLabel theDynamicalLable;

	public void widgetDefaultSelected(SelectionEvent event) {
		if(currentTask != null)
		{
			currentTask.stop();
		}
		currentTask = theTasks.get(((Combo)event.widget).getSelectionIndex());
		currentTask.start();
		theDynamicalLable.setTheValue( theValues.get(((Combo)event.widget).getSelectionIndex()));
	}

	public void widgetSelected(SelectionEvent event) {
		if(currentTask != null)
		{
			currentTask.stop();
		}
		currentTask = theTasks.get(((Combo)event.widget).getSelectionIndex());
		currentTask.start();
		theDynamicalLable.setTheValue( theValues.get(((Combo)event.widget).getSelectionIndex()));
	}

	public void setTasks(List<Task> tasks) {
		theTasks = tasks;
	}

	public void setDynamicalLable(DynamicalLabel dynamicalLable) {
		theDynamicalLable = dynamicalLable;
		theDynamicalLable.startUpadte();
	}

	public void setValues(List<UpdateValue> values) {
		theValues = values;
	}
}
