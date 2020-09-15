# Selenium-IAnnotationTransformer-Automation

##Find the below-detailed points about the tools/technology used in this project:
* Language: Java
* Used POM to organise the classes and packages
* Data driven tests from Excel using testNG dataprovider.
* Used TestNG framework to keep track of test cases getting passed, failed, and skipped.
* Used Maven to manage the dependencies and as build execution tool
* Drivers used:
-- Selenium-Java WebDriver  = 3.141.59
-- Chrome driver
-- Gecko driver


##Other Details:
* It will generate the Extent Report with the screenshots when test case fails. Easily customizable according to project need from TestRunDetails.properties.
* TestData from the excel sheet(/resources/testdata.xlsx) is fetched with the help of dataprovider and storing it in HashMap for easier processing.
-- In the RunManager sheet --> Choose the test cases you want to run by pass Yes under RunFlag column
-- In the TestData sheet ---> Choose the test data you want to pass to the testcase from excel sheet.
* Annotation transformer helps in setting the annotations for the test cases at run time including the ability to re-run the failed scripts
* The project highlights each element clicked on Web Page with Red border.


## Installation (pre-requisites)
1. JDK 1.8+ (make sure Java class path is set)
2. Maven (make sure .m2 class path is set)
3. Eclipse
4. Eclipse Plugins for
   * Maven
   * TestNG
5. Browser driver (make sure you have your desired browser drivers in lib folder under project directory).
   Currently this repository has below drivers:
   * Gecko driver version - [0.27.0](https://github.com/mozilla/geckodriver/releases/tag/v0.27.0), compatible with Mozilla Firefox version ≥79.
   * Chrome driver version - [84.0.4147.30](https://chromedriver.storage.googleapis.com/index.html?path=84.0.4147.30/), compatible with Chrome version 84


## Setup
* Install dependencies = mvn install


## Running your tests
* To run the test on Chrome Browser, run = mvn test -PSanityTest -DbrowserName.USER="chrome"
* To run the test on FireFox Browser, run = mvn test -PSanityTest -DbrowserName.USER="firefox"


## Extent Reports:
Generates HTML Extent report at project directory path: /ExtentReports/TestReport.html
