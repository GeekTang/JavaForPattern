/**
 * 
 */
package com.fd.application.wsmt.main;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.eclipse.swt.widgets.Display;

import com.fd.application.wsmt.gui.TaskChangeListener;
import com.fd.application.wsmt.gui.TaskChangeListenerImp;
import com.fd.application.wsmt.gui.TimeManageWin;
import com.fd.application.wsmt.persistence.Data;
import com.fd.application.wsmt.persistence.PropertiesFileData;
import com.fd.application.wsmt.task.TaskImp;
import com.fd.application.wsmt.task.TimeCostFormatImp;

/**
 * @author Administrator
 * 
 */
public class Runner {
	private static String dataFile = "data.txt";

	private static List<TaskImp> loadTask(Data data) {
		Properties properties = data.loadData(); 
		TimeCostFormatImp timeformat = new TimeCostFormatImp();
		List<TaskImp> tasks = new ArrayList<TaskImp>();
		Set<Entry<Object, Object>> sets = properties.entrySet();
		for (Entry<Object, Object> entry : sets) {
			TaskImp task = new TaskImp((String) entry.getKey(), timeformat,
					Integer.parseInt((String) entry.getValue()), data);
			tasks.add(task);
		}
		return tasks;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args != null && args.length >= 1) {
				dataFile = args[0];
			}
			TimeManageWin window = new TimeManageWin();

			Data data = new PropertiesFileData(dataFile);
			List tasks = loadTask(data);

			window.setTasks(tasks);
			window.setValues(tasks);
			TaskChangeListener theTaskChangeListener = new TaskChangeListenerImp();
			theTaskChangeListener.setTasks(tasks);
			window.setTaskChangeListener(theTaskChangeListener);
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
