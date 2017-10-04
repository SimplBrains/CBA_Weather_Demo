package com.subals.service.test.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.subals.beans.WeatherInfo;
import com.subals.dao.ReadCSV;

/*
 * Class Name: ReadCSVTest.java
 * Class Use : Test junit for ReadCSV.java. Read record of type WeatherInfo from a file with .csv extension.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 04-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class ReadCSVTest {

	final static Logger logger = Logger.getLogger(ReadCSVTest.class);
	ReadCSV rc;
	/* Method : init
	 * Function use : instantiates an object of class ReadCSV. 
	*/
	@Before
	public void init(){
		rc = new ReadCSV();
	}

	/* Method : testReadCSV
	 * Function use : Checks with dummy value that the code for function readCSV is working.
	 * readCSV use : Reads the data source and convert it into list of objects.
	*/
	@Test
	public void testReadCSV(){
		Properties prop =new Properties();
		try {
			InputStream input = new FileInputStream("src\\com\\subals\\properties\\app.properties");
			prop.load(input);
			List<WeatherInfo> weatherDataList = rc.readCSV(prop.getProperty("file"));
			for (Iterator<WeatherInfo> iterator = weatherDataList.iterator(); iterator.hasNext(); ) {
				Assert.assertEquals(true, iterator.next() instanceof WeatherInfo);
			}


		} catch (IOException e) {
			logger.error("properties file not found.");
			System.exit(-1);
		}
	}
}
