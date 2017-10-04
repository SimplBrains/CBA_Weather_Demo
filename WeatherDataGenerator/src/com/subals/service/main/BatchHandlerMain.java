package com.subals.service.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.subals.beans.WeatherInfo;
import com.subals.dao.ReadCSV;
import com.subals.dao.WriteCSV;
import com.subals.service.ConditionDefiner;
import com.subals.service.MeanCalculator;
import com.subals.service.MeanComparator;

/*
 * Class Name: BatchHandlerMain.java
 * Class Use : Invokes the process and generate weather prediction from previous year history data.
 * Pre-requisite : History data should be present for previous year.
 * Utility Objective : To get weather forcast dummy data.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 27-09-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class BatchHandlerMain {
	final static Logger logger = Logger.getLogger(BatchHandlerMain.class);
	public static void main(String[] args){

		Properties prop = new Properties();
		InputStream input = null;

		//date format definitions to be used
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss'Z'");

		String identifiedDate = null;
		String latestDate = null;

		try{
			//Load property file
			input = new FileInputStream("src\\com\\subals\\properties\\app.properties");		
			prop.load(input);
			logger.info("Property file loaded");

			String startDt= prop.getProperty("predictionStartDate");
			Date startDate = new SimpleDateFormat("yyyy-M-dd").parse((String) startDt);
			Calendar c = Calendar.getInstance();
			c.setTime(startDate);
			int dummyRecordCount=Integer.parseInt(prop.getProperty("noOfDummyRecords"));

			//read data source with the appended value after each update.
			ReadCSV readCSV = new ReadCSV();

			//batch processing for record generation based on declared dummy record count.
			for(int j=0;j<dummyRecordCount;j++){
				List<WeatherInfo> weatherDataList= readCSV.readCSV(prop.getProperty("file"));

				//remove the objects which are not of the defined position from the object set.
				for (Iterator<WeatherInfo> iterator = weatherDataList.iterator(); iterator.hasNext(); ) {
					WeatherInfo weatherInfo = iterator.next();
					if(!(prop.getProperty("position").equals(weatherInfo.getPos_lat()+","+weatherInfo.getPos_long()+","+weatherInfo.getPos_altitude()))){
						iterator.remove();
					}
				}
				//Calculate current year previous week mean and slide window mean
				MeanCalculator meanCalculator = new MeanCalculator();
				Double currentMean=meanCalculator.currentMean(weatherDataList, c.getTime());
				Map<String, Double> slideWindowMap = meanCalculator.slidingWindow(weatherDataList, c.getTime());

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
						identifiedRecord.setLt_date(formatDate.format(c.getTime()));
						identifiedRecord.setLt_time(formatTime.format(c.getTime()));
						identifiedRecord.setCondition(conditionDefiner.conditionDefine(Double.parseDouble(weatherDataList.get(i).getTemp()), weatherDataList.get(i).getPressure(), weatherDataList.get(i).getHumidity()));
						identifiedRecord.setTemp(weatherDataList.get(i).getTemp());
						identifiedRecord.setPressure(weatherDataList.get(i).getPressure());
						identifiedRecord.setHumidity(weatherDataList.get(i).getHumidity());
						latestDate=formatDate.format(c.getTime());
					}
				}

				WriteCSV writeCSVObj = new WriteCSV();
				if(!(identifiedRecord.getLocation()==null)){
					writeCSVObj.writeCSV(prop.getProperty("file"), identifiedRecord);
					logger.info("Dummy record generated for "+ latestDate);
				}else{
					throw new ParseException("", 1);
				}
				c.add(Calendar.DATE, 1);
				weatherDataList.clear();
			}
		}
		catch(IOException io){
			logger.error("Please check if the property file is correctly placed.");			
		}catch(ParseException pe){
			logger.error("Please provide previous year history data for "+identifiedDate+" to continue batch record generation.");
		}
		finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			logger.info("Dummy records generated till date "+latestDate);
			logger.info("************END OF PROGRAM****************");
		}
	}
}
