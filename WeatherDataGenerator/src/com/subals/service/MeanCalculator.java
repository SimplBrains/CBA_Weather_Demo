package com.subals.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.subals.beans.WeatherInfo;

/*
 * Class Name: MeanCalculator.java
 * Class Use : calculates the mean of current year previous week and last year sliding window 
 * 				mean. Return these mean values.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 02-10-2017
 * */

public class MeanCalculator {
	final static Logger logger = Logger.getLogger(MeanCalculator.class);
	public Double currentMean(List<WeatherInfo> weatherDataList,Date date) throws ParseException{

		double sum=0;
		int count=0;
		for(WeatherInfo weatherInfo : weatherDataList){
			Date d2 = new SimpleDateFormat("yyyy-M-dd").parse((String) weatherInfo.getLt_date());
			long diff = d2.getTime() - date.getTime();
			long dayDiff= diff/(1000*60*60*24);
			if(0>dayDiff && dayDiff >-8){
				sum+=Double.parseDouble(weatherInfo.getTemp())+weatherInfo.getPressure()+weatherInfo.getHumidity();
				count++;
				//System.out.println(d1.getTime()+"|"+d2.getTime()+"|"+diff+"|"+sum+"|"+count);
			}
		}
		double mean=sum/count;
		logger.info("current Year Prevoius week mean is "+mean);
		return mean;	
	}

	public Map<String, Double> slidingWindow(List<WeatherInfo> weatherDataList, Date date) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		String nowAsISO = df.format(date);
		String[] todayDate = nowAsISO.split("T")[0].split("-");
		//System.out.println((Integer.parseInt(todayDate[0])-1)+"-"+todayDate[1]+"-"+todayDate[2]);
		String prevYrDate = ((Integer.parseInt(todayDate[0])-1)+"-"+todayDate[1]+"-"+todayDate[2]).toString();
		Date d1 = new SimpleDateFormat("yyyy-M-dd").parse((String) prevYrDate);
		Map<Long,WeatherInfo> map = new HashMap<Long,WeatherInfo>();
		for(WeatherInfo weatherInfo : weatherDataList){
			Date d2 = new SimpleDateFormat("yyyy-M-dd").parse((String) weatherInfo.getLt_date());
			long diff = d2.getTime() - d1.getTime();
			long dayDiff= diff/(1000*60*60*24);
			if(7>dayDiff && dayDiff >-8){
				map.put(dayDiff, weatherInfo);
			}
		}
		return prevYearMean(map);
	}
	public Map<String, Double> prevYearMean(Map<Long,WeatherInfo> map){
		int diffStart=-8;
		double sum = 0;
		String sumDate = null;
		Map<String, Double> mapper= new HashMap<String, Double>();
		int count=0;
		while(diffStart<0){
			for(Map.Entry<Long, WeatherInfo> entry1 : map.entrySet()){
				if(entry1.getKey()>diffStart && entry1.getKey()<diffStart+7){
					if(entry1.getKey()==diffStart+1){
						sumDate=entry1.getValue().getLt_date();
					}
					sum+=Double.parseDouble(entry1.getValue().getTemp())+entry1.getValue().getPressure()+entry1.getValue().getHumidity();
					count++;
				}
			}
			double mean=sum/count;
			sum=0;count=0;
			if(!(sumDate==null)){
				mapper.put(sumDate, mean);
			}
			diffStart++;
		}
		if(mapper.size()==0){
			logger.error("No history record found for the defined location. Please provide history records or change the location. Process is existing.");
			System.exit(-1);
		}
		logger.info("Sliding window is "+mapper.toString());
		return mapper;
	}
}
