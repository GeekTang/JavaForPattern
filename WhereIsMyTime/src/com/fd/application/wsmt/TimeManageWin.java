package com.fd.application.wsmt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;

public class TimeManageWin extends ApplicationWindow{
	
	private List<TaskInformation> tasks;
	
	private List<UpdateValue> values;
	
	public List<UpdateValue> getValues() {
		return values;
	}

	public void setValues(List<UpdateValue> values) {
		this.values = values;
	}

	public List<TaskInformation> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskInformation> tasks) {
		this.tasks = tasks;
	}

	public TaskChangeListener getTaskChangeListener() {
		return taskChangeListener;
	}

	public void setTaskChangeListener(TaskChangeListener taskChangeListener) {
		this.taskChangeListener = taskChangeListener;
	}

	private TaskChangeListener taskChangeListener;

	/**
	 * Create the application window.
	 */
	public TimeManageWin() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		//Tasks selection area
		Combo combo = new Combo(container, SWT.NONE);
		combo.setBounds(86, 49, 120, 25);
		List<String> taskNames = new ArrayList<String>();
		
		//Task selection area
		Label taskName = new Label(container, SWT.NONE);
		taskName.setBounds(86, 23, 120, 20);
		taskName.setText("Doing : ");
		
		//Task time spend logo
		Label timeSpende = new Label(container, SWT.NONE);
		timeSpende.setBounds(256, 23, 91, 20);
		timeSpende.setText("Time Spent : ");
		String[] names = new String[tasks.size()];
		//initial the task list
		for(TaskInformation task : tasks)
		{
			taskNames.add(task.getTaskName());
		}
		combo.setItems(taskNames.toArray(names));
		
		combo.addSelectionListener(taskChangeListener);
		
		Label timeSpendValue = new Label(container, SWT.NONE);
		timeSpendValue.setBounds(256, 49, 91, 25);
		
		DynamicalLabel dynamicalLabel = new LabelUpdatingTask(timeSpendValue, 1);
		Display.getCurrent().syncExec(dynamicalLabel);
		taskChangeListener.setDynamicalLable(dynamicalLabel);
		taskChangeListener.setValues(values);
		
		combo.select(0);

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TimeManageWin window = new TimeManageWin();
			List theTasks = new ArrayList<TaskImp>();
			
			TimeCostFormatImp timeformat = new TimeCostFormatImp();
			TaskImp coding = new TaskImp("Coding", timeformat);
			theTasks.add(coding);
			
			TaskImp reading = new TaskImp("Reading", timeformat);
			theTasks.add(reading);
			
			window.setTasks(theTasks);
			window.setValues(theTasks);
			TaskChangeListener theTaskChangeListener = new TaskChangeListenerImp();
			theTaskChangeListener.setTasks(theTasks);
			window.setTaskChangeListener(theTaskChangeListener );
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Where is My Time 0.1");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
