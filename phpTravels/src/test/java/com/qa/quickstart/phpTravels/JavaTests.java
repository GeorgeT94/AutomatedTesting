package com.qa.quickstart.phpTravels;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class JavaTests {

	ChromeDriver driver;
	static ExtentReports extent;
	static ExtentTest test;
	
	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Testing\\chromedriver.exe");
		
		
		String filePath = "C:\\Users\\Admin\\Desktop\\demoQAreport.html";
		
		extent = new ExtentReports(filePath, true);

		
		
		test = extent.startTest("Verify application Title");

		// add a note to the test
		test.log(LogStatus.INFO, "Browser started");

		// report the test as a pass
		test.log(LogStatus.PASS, "verify Title of the page");


	}

	@Before
	public void before() {
		driver = new ChromeDriver();
		
	 }
	
	@After
	public void tearDown() {
		try { Thread.sleep(500); } catch(Exception e) {} 
		driver.close();
		extent.flush();
	}
}
