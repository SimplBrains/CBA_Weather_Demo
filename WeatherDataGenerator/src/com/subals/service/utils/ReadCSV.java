package com.subals.service.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.subals.beans.WeatherInfo;

/*
 * Class Name: ReadCSV.java
 * Class Use : Read record of type WeatherInfo from a file with .csv extension.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 02-10-2017
 * */

public class ReadCSV {
	final static Logger logger = Logger.getLogger(ReadCSV.class);
	public List<WeatherInfo> readCSV(String file) throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));

		// read file line by line
		String line = null;
		Scanner scanner = null;
		int index = 0;
		List<WeatherInfo> weatherDataList = new ArrayList<WeatherInfo>();

		while ((line = reader.readLine()) != null) {
			WeatherInfo weatherInfo = new WeatherInfo();
			scanner = new Scanner(line);
			scanner.useDelimiter("\\|");
			while (scanner.hasNext()) {
				String data = scanner.next();				
				if (index == 0){
					weatherInfo.setLocation(data);
				}
				else if (index == 1){
					String[] str = data.split(",");
					weatherInfo.setPos_lat(Float.parseFloat(str[0]));
					weatherInfo.setPos_long(Float.parseFloat(str[1]));
					weatherInfo.setPos_altitude(Integer.parseInt(str[2]));
				}
				else if (index == 2){
					String[] str = data.split("T");
					weatherInfo.setLt_date(str[0]);
					weatherInfo.setLt_time(str[1]);
				}
					
				else if (index == 3){
					weatherInfo.setCondition(data);
				}
				else if (index == 4){
					weatherInfo.setTemp(data);
				}
				else if (index == 5){
					weatherInfo.setPressure(Double.parseDouble(data));
				}
				else if (index == 6){
					weatherInfo.setHumidity(Short.parseShort(data));
				}
				else
					logger.info("invalid data::" + data);
				index++;
			}
			index = 0;
			weatherDataList.add(weatherInfo);
		}
		
		//close reader
		reader.close();
		
		return weatherDataList;
	}

}
