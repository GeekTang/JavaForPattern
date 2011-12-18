package com.fd.application.wsmt;

import org.eclipse.swt.widgets.Label;

public class LabelUpdatingTask implements DynamicalLabel {

	private Label theLabel;

	private UpdateValue theValue;

	public UpdateValue getTheValue() {
		return theValue;
	}

	public void setTheValue(UpdateValue theValue) {
		this.theValue = theValue;
		refresh();
	}

	private int interval;

	private boolean running;

	public LabelUpdatingTask(Label label, int updateInterval) {
		theLabel = label;
		interval = updateInterval;
	}
	
	private void refresh()
	{
		if (theValue != null) {
			theLabel.setText(theValue.getValue());
		}
	}
	
	public void startUpadte()
	{
		running = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		while (running) {
			refresh();
			try {
				Thread.sleep(interval * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopUpdate() {
		running = false;
	}

}
