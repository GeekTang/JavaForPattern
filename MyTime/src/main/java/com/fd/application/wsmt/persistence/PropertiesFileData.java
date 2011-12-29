/**
 * 
 */
package com.fd.application.wsmt.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class PropertiesFileData implements Data {
	
	private String tehFileName;
	
	private Properties properties;
	
	public PropertiesFileData(String fileName)
	{
		tehFileName = fileName;
		properties = new Properties();
		File file = new File(tehFileName);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			properties.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.fd.application.wsmt.persistence.Data#loadData()
	 */
	public Properties loadData() {
		return properties;
	}

	/* (non-Javadoc)
	 * @see com.fd.application.wsmt.persistence.Data#record(java.lang.String, java.lang.String)
	 */
	public boolean record(String key, String value) {
		properties.setProperty(key, value);
		File file = new File(tehFileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			properties.store(out, "Update key : " + key + " to value : " + value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.fd.application.wsmt.persistence.Data#restore(java.util.Properties)
	 */
	public boolean restore(Properties properties) {
		this.properties = properties;
		File file = new File(tehFileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			properties.store(out, "Restore whole propertis");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
