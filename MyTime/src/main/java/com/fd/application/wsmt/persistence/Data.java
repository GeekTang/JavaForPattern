package com.fd.application.wsmt.persistence;

import java.util.Properties;

public interface Data {

	Properties loadData();
	
	boolean record(String key, String value);
	
	boolean restore(Properties properties);
}
