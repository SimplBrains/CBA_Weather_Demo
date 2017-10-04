package com.subals.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.subals.beans.WeatherInfo;
import com.subals.service.MeanCalculator;

/*
 * Class Name: MeanCalculatorTest.java
 * Class Use : Test junit for MeanCalculator.java. Calculates the mean of current year previous week and last year sliding window 
 * 				mean. Return these mean values.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 04-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class MeanCalculatorTest {
	final static Logger logger = Logger.getLogger(MeanCalculatorTest.class);
	MeanCalculator mc;
	/* Method : init
	 * Function use : instantiates an object of class MeanCalculator. 
	*/
	@Before
	public void init(){
		mc = new MeanCalculator();
	}
	
	
	/* Method : testCurrentMean
	 * Function use : Checks with dummy value that the code for function currentMean is working.
	 * currentMean use : This function identifies previous week data and calculates mean for the identified data. Then it returns the 
	 * calculated mean.
	*/	
	@Test
	public void testCurrentMean(){
		WeatherInfo wi = new WeatherInfo();
		wi.setLocation("Sydney0");
		wi.setPos_lat(Float.parseFloat("-33.86"));
		wi.setPos_long(Float.parseFloat("151.21"));
		wi.setPos_altitude(39);
		wi.setLt_time("05:02:12Z");
		wi.setCondition("Rain");
		wi.setTemp("+12.5");
		wi.setPressure(1004.3);
		wi.setHumidity(Short.parseShort("97"));
		List<WeatherInfo> weatherDataList = new ArrayList<WeatherInfo>();

		try {
			String prevYrDate = "2017-10-03";
			Date d1 = new SimpleDateFormat("yyyy-M-dd").parse((String) prevYrDate);

			wi.setLt_date("2017-09-29");
			weatherDataList.add(wi);
			Assert.assertEquals(Double.valueOf(1113.8), mc.currentMean(weatherDataList, d1));
		} catch (ParseException e) {
			logger.error("Parse Exception Occurred. Please consult technical team.");
		}
	}
	
	/* Method : testSlidingWindow
	 * Function use : Checks with dummy value that the code for function slidingWindow is working.
	 * slidingWindow use : This function identifies previous year previous week data and calculates mean for the identified data. Then it returns the 
	 * calculated mean with help of prevYearMean function. 
	*/
	@Test
	public void testSlidingWindow(){
		WeatherInfo wi = new WeatherInfo();
		wi.setLocation("Sydney0");
		wi.setPos_lat(Float.parseFloat("-33.86"));
		wi.setPos_long(Float.parseFloat("151.21"));
		wi.setPos_altitude(39);
		wi.setLt_time("05:02:12Z");
		wi.setCondition("Rain");
		wi.setTemp("+12.5");
		wi.setPressure(1004.3);
		wi.setHumidity(Short.parseShort("97"));
		List<WeatherInfo> weatherDataList = new ArrayList<WeatherInfo>();

		try {
			String prevYrDate = "2017-10-03";
			Date d1 = new SimpleDateFormat("yyyy-M-dd").parse((String) prevYrDate);
			wi.setLt_date("2016-10-03");
			weatherDataList.add(wi);
			Assert.assertEquals("{2016-10-03=1113.8}", mc.slidingWindow(weatherDataList, d1).toString());
		} catch (ParseException e) {
			logger.error("Parse Exception Occurred. Please consult technical team.");
		}
	}
}
