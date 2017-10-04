package com.subals.test.testall;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.subals.test.dao.ReadCSVTest;
import com.subals.test.dao.WriteCSVTest;
import com.subals.test.service.ConditionDefinerTest;
import com.subals.test.service.MeanCalculatorTest;
import com.subals.test.service.MeanComparatorTest;

/*
 * Class Name: TestAll.java
 * Class Use : Test all the junits together.Included junits are ConditionDefinerTest, MeanCalculatorTest, MeanComparatorTest, 
 * 				ReadCSVTest, WriteCSVTest
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 04-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */


@RunWith(Suite.class)
@SuiteClasses({ConditionDefinerTest.class, MeanCalculatorTest.class, MeanComparatorTest.class, ReadCSVTest.class, WriteCSVTest.class})
public class TestAll {

}
