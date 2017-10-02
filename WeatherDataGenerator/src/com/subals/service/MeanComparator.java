package com.subals.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/*
 * Class Name: MeanComparator.java
 * Class Use : compares the mean of current year previous week and last year sliding window 
 * 				mean and find best window. Find start date of best window and returns the same.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 02-10-2017
 * */

public class MeanComparator {
	final static Logger logger = Logger.getLogger(MeanComparator.class);
	public String meanComparision(Double currentMean, Map<String, Double> slideWindowMap) throws ParseException{
		TreeMap<Float, String> delta = new TreeMap<Float, String>();
		for(Entry<String, Double> entry1 : slideWindowMap.entrySet()){
			delta.put(Float.parseFloat(Double.toString(entry1.getValue() -  currentMean).replaceAll("-", "")), entry1.getKey());
		}
		return correctMeanFinder(delta);
	}
	
	public String correctMeanFinder(TreeMap<Float, String> delta) throws ParseException{
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
