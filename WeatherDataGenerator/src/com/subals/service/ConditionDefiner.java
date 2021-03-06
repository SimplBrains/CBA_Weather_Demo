package com.subals.service;

import org.apache.log4j.Logger;

import com.subals.test.service.ConditionDefinerTest;

/*
 * Class Name: ConditionDefiner.java
 * Class Use : defines condition of weather based on temperature, pressure and humidity.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 02-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class ConditionDefiner {
	final static Logger logger = Logger.getLogger(ConditionDefinerTest.class);
	/* Method : conditionDefine
	 * Input Parameters : temperature, pressure, humidity
	 * Output Parameters : condition
	 * Function use : This function analyze the condition based on the input parameters. 
	*/
	public String conditionDefine(double temp, double pressure, double humidity){

		String condition="";
		if(temp < 0.0 && pressure>=500.0 && pressure<=1000.0 && humidity>=15 && humidity <=60){
			condition="Snow";
		}
		else if(temp >= 0.0 && temp <= 20.0 && pressure>1000.0 && pressure<=1200.0 && humidity>60 && humidity <=100){
			condition="Rain";
		}else if(temp >20.0 && temp <=50.0 && pressure>1200.0 && pressure<=1500.0 && humidity<15){
			condition="Sunny";
		}
		logger.info("Condition identified is "+condition);
		return condition;
	}
}
