package com.subals.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.subals.service.ConditionDefiner;

/*
 * Class Name: ConditionDefinerTest.java
 * Class Use : Test junit for ConditionDefiner.java. Defines condition of weather based on temperature, pressure and humidity.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 04-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class ConditionDefinerTest {
	ConditionDefiner cd;
	/* Method : init
	 * Function use : instantiates an object of class ConditionDefiner. 
	*/
	@Before
	public void init(){
		cd = new ConditionDefiner();
	}

	/* Method : testConditionDefine
	 * Function use : Checks with dummy value that the code for function conditionDefine is working.
	 * conditionDefine use : This function analyze the condition based on the input parameters. 
	*/
	@Test
	public void testConditionDefine(){
		Assert.assertEquals("Rain", cd.conditionDefine(0.0, 1200.0, 100));
		Assert.assertEquals("Sunny", cd.conditionDefine(50.0, 1500.0, 14));
		Assert.assertEquals("Snow", cd.conditionDefine(-0.1, 500.0, 15));
	}
}
