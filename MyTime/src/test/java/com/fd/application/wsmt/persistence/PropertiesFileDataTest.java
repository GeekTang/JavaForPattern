/**
 * 
 */
package com.fd.application.wsmt.persistence;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Administrator
 *
 */
public class PropertiesFileDataTest extends TestCase {

	/**
	 * Test method for {@link com.fd.application.wsmt.persistence.PropertiesFileData#loadData()}.
	 * @throws URISyntaxException 
	 */
	public void testLoadData() throws URISyntaxException {
		File file = new File(this.getClass().getResource("data.txt").toURI());
		Data data = new PropertiesFileData(file.getAbsolutePath());
		Properties properties = data.loadData();
		Assert.assertEquals("120", properties.get("Meeting"));
	}

	/**
	 * Test method for {@link com.fd.application.wsmt.persistence.PropertiesFileData#record(java.lang.String, java.lang.String)}.
	 * @throws URISyntaxException 
	 */
	public void testRecord() throws URISyntaxException {
		File file = new File(this.getClass().getResource("data.txt").toURI());
		Data data = new PropertiesFileData(file.getAbsolutePath());
		Assert.assertEquals(true, data.record("Test", "60"));
	}

	/**
	 * Test method for {@link com.fd.application.wsmt.persistence.PropertiesFileData#restore(java.util.Properties)}.
	 * @throws URISyntaxException 
	 */
	public void testRestore() throws URISyntaxException {
		File file = new File(this.getClass().getResource("data.txt").toURI());
		Data data = new PropertiesFileData(file.getAbsolutePath());
		Properties properties = data.loadData();
		properties.setProperty("RecordTest", "100");
		data.restore(properties);
	}

}
