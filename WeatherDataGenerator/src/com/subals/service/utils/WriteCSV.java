package com.subals.service.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.subals.beans.WeatherInfo;

/*
 * Class Name: WriteCSV.java
 * Class Use : Writes record of type WeatherInfo to a file with .csv extension.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 02-10-2017
 * */

public class WriteCSV {
	final static Logger logger = Logger.getLogger(WriteCSV.class);
	public void writeCSV(String file, WeatherInfo weatherData) throws IOException{
		StringBuilder data = new StringBuilder();
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);

			data.append("\n");
			data.append(weatherData.getLocation());
			data.append("|");
			data.append(weatherData.getPos_lat());
			data.append(",");
			data.append(weatherData.getPos_long());
			data.append(",");
			data.append(weatherData.getPos_altitude());
			data.append("|");
			data.append(weatherData.getLt_date());
			data.append("T");
			data.append(weatherData.getLt_time());
			data.append("|");
			data.append(weatherData.getCondition());
			data.append("|");
			data.append(weatherData.getTemp());
			data.append("|");
			data.append(weatherData.getPressure());
			data.append("|");
			data.append(weatherData.getHumidity());
		bw.write(data.toString());

		logger.info("Data Written successfully !");
		try {

			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}
	}

}
