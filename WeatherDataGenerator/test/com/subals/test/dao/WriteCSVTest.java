package com.subals.test.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.subals.beans.WeatherInfo;
import com.subals.dao.WriteCSV;

/*
 * Class Name: WriteCSVTest.java
 * Class Use : Test junit for WriteCSV.java. Writes record of type WeatherInfo to a file with .csv extension.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 04-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class WriteCSVTest {

	final static Logger logger = Logger.getLogger(ReadCSVTest.class);
	WriteCSV wc;
	/* Method : init
	 * Function use : instantiates an object of class WriteCSV. 
	*/
	@Before
	public void init(){
		wc = new WriteCSV();
	}

	/* Method : testWriteCSV
	 * Function use : Checks with dummy value that the code for function writeCSV is working.
	 * writeCSV use : Writes to the data source.   
	*/
	@Test
	public void testWriteCSV(){
		WeatherInfo wi = new WeatherInfo();
		wi.setLocation("Sydney0");
		wi.setPos_lat(Float.parseFloat("-33.86"));
		wi.setPos_long(Float.parseFloat("151.21"));
		wi.setPos_altitude(39);
		wi.setLt_date("2016-10-03");
		wi.setLt_time("05:02:12Z");
		wi.setCondition("Rain");
		wi.setTemp("+12.5");
		wi.setPressure(1004.3);
		wi.setHumidity(Short.parseShort("97"));

		Properties prop =new Properties();
		try {
			InputStream input = new FileInputStream("src\\com\\subals\\properties\\app.properties");
			prop.load(input);

			Assert.assertEquals(true, wc.writeCSV(prop.getProperty("file"),wi));

		} catch (IOException e) {
			logger.error("properties file not found.");
			System.exit(-1);
		}
	}

}
