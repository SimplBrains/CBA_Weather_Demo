package com.subals.test.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.subals.service.MeanComparator;

/*
 * Class Name: MeanComparatorTest.java
 * Class Use : Test junit for MeanComparator.java. Compares the mean of current year previous week and last year sliding window 
 * 				mean and find best window. Find start date of best window and returns the same.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 04-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class MeanComparatorTest {
	final static Logger logger = Logger.getLogger(MeanComparatorTest.class);
	MeanComparator mc;
	/* Method : init
	 * Function use : instantiates an object of class MeanComparator. 
	*/
	@Before
	public void init(){
		mc = new MeanComparator();
	}

	/* Method : testMeanComparision
	 * Function use : Checks with dummy value that the code for function meanComparision is working.
	 * meanComparision use : This function compares the mean of current year previous week data and the available window mean with the 
	 * help of correctMeanFinder function. It return the predicted date for replication.
	*/
	@Test
	public void testMeanComparision(){
		Double currentMean = 1113.8;
		Map<String, Double> slideWindowMap = new HashMap<String, Double>();
		slideWindowMap.put("2016-10-03",1113.8);

		try {
			Assert.assertEquals("2016-10-10", mc.meanComparision(currentMean, slideWindowMap));
		} catch (ParseException e) {
			logger.error("Parse Exception Occurred. Please consult technical team.");
		}
	}
}
