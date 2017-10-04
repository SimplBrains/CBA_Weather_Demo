# CBA_Weather_Data_Generator_Demo

## Synopsis

This Project is a prototype of a program which articially simulates the weather and outputs weather data in a standard format for a game to read.

## Algorithm used for the project

This project uses sliding window algorithm. 

Sliding window algorithm - Mean of current year last week data is calcualted. Also, sliding window is identified for last year 14 days data. Mean is calculated for each sliding window. Best Matching mean between current year and slinding window is selected on the basis of proximity of the data. Then the last year date is identified on the basis of selected mean. This identified date data is replicated for the prediction date.

We have used batch pocessing which takes 2 parameters - the start date and number of records to be generated. It iterates the next date starting from 'the start date' and keep incrementing the date till the 'number of records' limit is reached.

## API Reference

Software libraries used are jdk1.8.0_144 and log4j-1.2.17.

## Software’s required to run this project

Softwares required : jre1.8.0_144 

## Explanation on how to configure the project for running it

For Production : 
1. Go to https://github.com/SimplBrains/CBA_Weather_Demo and download 'WeatherDataGenerator_lib', 'WeatherDataGenerator.jar' and 'program_caller.sh'
2. Place these three files in one folder and place it in any machine with Java platform.
3. Call command - 'sh program_caller.sh'.

For Development :
1. Go to https://github.com/SimplBrains/CBA_Weather_Demo and download 'WeatherDataGenerator'. This is workspace.
2. Import the workspace in Eclipse.
3. run 'com.subals.service.main.BatchHandlerMain.java'.

## Class diagram of the code
Please find the class diagram at location : 
https://github.com/SimplBrains/CBA_Weather_Demo/blob/master/WeatherDataGeneratorClass%20Diagram.png

## Main classes used in the project
1.BatchHandlerMain - Invokes the process and generate weather prediction from previous year history data.
2.TodayPredictorMain - Invokes the process and generate weather prediction for current date(i.e. today).(for debug/future used utility)
3.MeanCalculator - calculates the mean of current year previous week and last year sliding window mean. Return these mean values.
4.MeanComparator - compares the mean of current year previous week and last year sliding window mean and find best window. Find start date of best window and returns the same.
5.ConditionDefiner - defines condition of weather based on temperature, pressure and humidity.
6.ReadCSV - Read record of type WeatherInfo from a file with .csv extension.
7.WriteCSV - Writes record of type WeatherInfo to a file with .csv extension.
8.WeatherInfo - Bean class for data source parameter.

## Test

For Testing :
1. Go to https://github.com/SimplBrains/CBA_Weather_Demo and download 'WeatherDataGenerator'. This is workspace.
2. Import the workspace in Eclipse.
3. run 'com.subals.test.testall.TestAll' in 'test' folder as junit.

## Contributors

Kumar Abhishek - https://www.linkedin.com/in/kumar-abhishek-857a8b8/

## License

This code belongs to Kumar Abhishek, NSR Registration : 741068199096. Any use, modification without his permission is strictly prohibited.
