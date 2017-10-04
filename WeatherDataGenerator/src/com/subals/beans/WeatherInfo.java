package com.subals.beans;

/*
 * Class Name: WeatherInfo.java
 * Class Use : Bean class for data source parameter.
 * Author : Kumar Abhishek(558399, TCS)
 * Date : 02-10-2017
 * Copyright : This code belongs to Kumar Abhishek, NSR Registration : 741068199096 and is intended for demonstration to 
 * CBA only. Any other use, modification without his permission is strictly prohibited.
 * */

public class WeatherInfo {
	private String location;
	private float pos_lat;
	private float pos_long;
	private int pos_altitude;
	private String lt_date;
	private String lt_time;
	private String condition;
	private String temp;
	private double pressure;
	private short humidity;
	//all the below functions are getters and setters for above defined instance variables.
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getPos_lat() {
		return pos_lat;
	}
	public void setPos_lat(float pos_lat) {
		this.pos_lat = pos_lat;
	}
	public float getPos_long() {
		return pos_long;
	}
	public void setPos_long(float pos_long) {
		this.pos_long = pos_long;
	}
	public int getPos_altitude() {
		return pos_altitude;
	}
	public void setPos_altitude(int pos_altitude) {
		this.pos_altitude = pos_altitude;
	}
	public String getLt_date() {
		return lt_date;
	}
	public void setLt_date(String lt_date) {
		this.lt_date = lt_date;
	}
	public String getLt_time() {
		return lt_time;
	}
	public void setLt_time(String lt_time) {
		this.lt_time = lt_time;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public short getHumidity() {
		return humidity;
	}
	public void setHumidity(short humidity) {
		this.humidity = humidity;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
}
