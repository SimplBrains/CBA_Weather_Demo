package com.subals.service.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.subals.beans.WeatherInfo;
import com.subals.service.ConditionDefiner;
import com.subals.service.MeanCalculator;
import com.subals.service.MeanComparator;
import com.subals.service.utils.ReadCSV;
import com.subals.service.utils.WriteCSV;
/*
 * Class Name: HandlerMain.java
 * Class Use : Invokes the process and generate weather prediction for current date(i.e. today).
 * Utility Objective : To get weather forcast data.
 * Pre-requisite : History data should be present for previous year.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 27-09-2017
 * */

public class TodayPredictorMain {
	final static Logger logger = Logger.getLogger(TodayPredictorMain.class);
	public static void main(String[] args){
		Properties prop = new Properties();
		InputStream input = null;
				
		//date format definitions to be used
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss'Z'");
		
		String identifiedDate = null;
		
		try{
		//Load property file
		input = new FileInputStream("src\\com\\subals\\properties\\app.properties");		
		prop.load(input);
		logger.info("Property file loaded");
		
		//read data source
		ReadCSV readCSV = new ReadCSV();
		List<WeatherInfo> weatherDataList= readCSV.readCSV(prop.getProperty("file"));

		//remove the objects which are not of the defined position from the object set.
		for (Iterator<WeatherInfo> iterator = weatherDataList.iterator(); iterator.hasNext(); ) {
		    WeatherInfo weatherInfo = iterator.next();
		    if(!(prop.getProperty("position").equals(weatherInfo.getPos_lat()+","+weatherInfo.getPos_long()+","+weatherInfo.getPos_altitude()))){
		    	iterator.remove();
			}
		}
		

		//Calculate current year previous week mean and slide window means
		MeanCalculator meanCalculator = new MeanCalculator();
		Double currentMean=meanCalculator.currentMean(weatherDataList, new Date());
		Map<String, Double> slideWindowMap = meanCalculator.slidingWindow(weatherDataList, new Date());
		
		//Get the right window out of sliding window
		MeanComparator meanComparator = new MeanComparator();
		identifiedDate = meanComparator.meanComparision(currentMean, slideWindowMap);
		
		//populate the identified record
		WeatherInfo identifiedRecord = new WeatherInfo();
		ConditionDefiner conditionDefiner = new ConditionDefiner();
		
		for (int i = 0; i < weatherDataList.size(); i++) {
			if(weatherDataList.get(i).getLt_date().equals(identifiedDate)){
				identifiedRecord.setLocation(weatherDataList.get(i).getLocation());
				identifiedRecord.setPos_lat(weatherDataList.get(i).getPos_lat());
				identifiedRecord.setPos_long(weatherDataList.get(i).getPos_long());
				identifiedRecord.setPos_altitude(weatherDataList.get(i).getPos_altitude());
				identifiedRecord.setLt_date(formatDate.format(new Date()));
				identifiedRecord.setLt_time(formatTime.format(new Date()));
				identifiedRecord.setCondition(conditionDefiner.conditionDefine(Double.parseDouble(weatherDataList.get(i).getTemp()), weatherDataList.get(i).getPressure(), weatherDataList.get(i).getHumidity()));
				identifiedRecord.setTemp(weatherDataList.get(i).getTemp());
				identifiedRecord.setPressure(weatherDataList.get(i).getPressure());
				identifiedRecord.setHumidity(weatherDataList.get(i).getHumidity());
			}
		}
		
		//write prediction to datasource
		WriteCSV writeCSVObj = new WriteCSV();
		if(!(identifiedRecord.getLocation()==null)){
		writeCSVObj.writeCSV(prop.getProperty("file"), identifiedRecord);
		logger.info("Dummy record generated for "+ formatDate.format(new Date()));
		}else{
			throw new ParseException("", 1);
		}
		}
		catch(IOException io){
			logger.error("Please check if the property file is correctly placed.");	
			io.getStackTrace();
		}catch(ParseException pe){
			logger.error("Please provide previous year history data for "+identifiedDate+" for today's weather prediction.");
			pe.getStackTrace();
		}
		finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			logger.info("Dummy records generated for date "+formatDate.format(new Date()));
			logger.info("************END OF PROGRAM****************");
		}
	}
}
