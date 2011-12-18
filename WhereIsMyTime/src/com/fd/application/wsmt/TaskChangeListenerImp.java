package com.fd.application.wsmt;

import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class TaskChangeListenerImp implements TaskChangeListener {
	
	private List<UpdateValue> theValues;
	
	private List<Task> theTasks;
	
	private Task currentTask;
	
	private DynamicalLabel theDynamicalLable;

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		if(currentTask != null)
		{
			currentTask.stop();
		}
		currentTask = theTasks.get(((Combo)event.widget).getSelectionIndex());
		currentTask.start();
		theDynamicalLable.setTheValue( theValues.get(((Combo)event.widget).getSelectionIndex()));
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		if(currentTask != null)
		{
			currentTask.stop();
		}
		currentTask = theTasks.get(((Combo)event.widget).getSelectionIndex());
		currentTask.start();
		theDynamicalLable.setTheValue( theValues.get(((Combo)event.widget).getSelectionIndex()));
	}

	@Override
	public void setTasks(List<Task> tasks) {
		theTasks = tasks;
	}

	@Override
	public void setDynamicalLable(DynamicalLabel dynamicalLable) {
		theDynamicalLable = dynamicalLable;
		theDynamicalLable.startUpadte();
	}

	@Override
	public void setValues(List<UpdateValue> values) {
		theValues = values;
	}
}
