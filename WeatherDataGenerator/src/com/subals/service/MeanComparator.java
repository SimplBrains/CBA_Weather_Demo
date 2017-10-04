package com.subals.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.subals.test.service.MeanComparatorTest;

/*
 * Class Name: MeanComparator.java
 * Class Use : compares the mean of current year previous week and last year sliding window 
 * 				mean and find best window. Find start date of best window and returns the same.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 02-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class MeanComparator {
	final static Logger logger = Logger.getLogger(MeanComparatorTest.class);
	/* Method : meanComparision
	 * Input Parameters : currentMean, slideWindowMap with key as date of window and value as mean of window.
	 * Output Parameters : Identified date which is to be replicated for new prediction.
	 * Function use : This function compares the mean of current year previous week data and the available window mean with the 
	 * help of correctMeanFinder function. It return the predicted date for replication.   
	*/
	public String meanComparision(Double currentMean, Map<String, Double> slideWindowMap) throws ParseException{
		TreeMap<Float, String> delta = new TreeMap<Float, String>();
		for(Entry<String, Double> entry1 : slideWindowMap.entrySet()){
			delta.put(Float.parseFloat(Double.toString(entry1.getValue() -  currentMean).replaceAll("-", "")), entry1.getKey());
		}
		return correctMeanFinder(delta);
	}
	
	/* Method : correctMeanFinder
	 * Input Parameters : TreeMap with key as delta difference between current year previous week mean and sliding window mean 
	 * and value as the date of sliding window.
	 * Output Parameters : Identified date for duplication of data to predicted date.
	 * Function use : Helper function for meanComparision function.
	*/
	private String correctMeanFinder(TreeMap<Float, String> delta) throws ParseException{
		String dateWindow = delta.firstEntry().getValue();
		Date date = new SimpleDateFormat("yyyy-M-dd").parse((String) dateWindow);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 7);
		String identifiedDate = sdf.format(c.getTime());
		logger.info("Identified previous year date for replication is "+identifiedDate+"("+dateWindow+" +7 Days).");
		return identifiedDate;
	}

}
