package com.qa.quickstart.demoQA;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.TestCase;


public class demoQaTests{
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
	
	@Test
	public void testDraggable() {
		Draggable dragTest = PageFactory.initElements(driver, Draggable.class);
		driver.manage().window().maximize();
		String url = "http://demoqa.com/draggable/";
		driver.navigate().to(url);

		Point coordinates = driver.findElement(By.id("draggable")).getLocation();
		dragTest.moveBox();
		
		Point finalCoords = driver.findElement(By.id("draggable")).getLocation();
		test = extent.startTest("Check if object can be dragged");
		
		
		
		try {
			assertTrue("no position change", (finalCoords.getX() - coordinates.getX()) > 200 && (finalCoords.getY() - coordinates.getY()) > 100);
			test.log(LogStatus.PASS, "Box position was successfully moved");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
	}
	@Test
	public void testSelectable() {
		test = extent.startTest("Check if elements cna be selected by clicking and dragging");
		Selectable select = PageFactory.initElements(driver, Selectable.class);
		driver.manage().window().maximize();
		String url = "http://demoqa.com/selectable/";
		driver.navigate().to(url);

		select.selectBoxes();
		List<WebElement> elementList = select.selectedBoxes();
		
		
		
		
		try {
			assertTrue("not all elements selected", elementList.size() == 7);
			test.log(LogStatus.PASS, "All boxes were selected");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
	}
	
	@Test
	public void testAccordian() {
		test = extent.startTest("Test if accordian elemnt can be expanded when clicked on");
		driver.manage().window().maximize();
		String url = "http://demoqa.com/accordion/";
		driver.navigate().to(url);
		
		Accordian accord = PageFactory.initElements(driver, Accordian.class);
		
		
		
		try {
			accord.testAccordian();
			test.log(LogStatus.PASS, "Accordian elements can be expanded");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
	}
	
	
	@Test
	public void testAutoComplete() {
		driver.manage().window().maximize();
		String url = "http://demoqa.com/autocomplete/";
		driver.navigate().to(url);
		
		AutoComplete autoComplete = PageFactory.initElements(driver, AutoComplete.class);
		List<String> autoCompleteList;

		test = extent.startTest("Test if dropdown menu contains relevant search results");

		try {
			autoCompleteList = autoComplete.getDropDownList("ja");
			
			assertTrue(autoCompleteList.contains("Java"));
			test.log(LogStatus.PASS, "Java appears when searching 'ja'");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
		try {
			autoCompleteList = autoComplete.getDropDownList("ja");
			
			assertTrue(autoCompleteList.contains("JavaScript"));
			test.log(LogStatus.PASS, "JavaScript appears when searching 'ja'");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
		try {
			autoCompleteList = autoComplete.getDropDownList("sc");
			assertTrue(autoCompleteList.contains("Scala"));
			
			test.log(LogStatus.PASS, "Scala appears when searching 'sc'");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
		
	}
	
	@Test
	public void testDatePicker() {

		test = extent.startTest("See if date picker gives the correct date when clicked on");
		//Navigating to the web page
		driver.manage().window().maximize();
		String url = "http://demoqa.com/datepicker/";
		driver.navigate().to(url);
		
		DatePicker datePicker = PageFactory.initElements(driver, DatePicker.class);

		//clicking on the datepicker bar and checking if a datepicker ui is displayed
		
		datePicker.clickDateBox();
		WebElement datePickerUI= driver.findElementById("ui-datepicker-div");

		
		try {
			assertTrue("datepicker ui is not displayed", datePickerUI.isDisplayed());
			
			test.log(LogStatus.PASS, "Date picker ui is displayed");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
		
		int monthsBack = 5;
		int date = 24;
		

		String dateString = datePicker.getDateString(monthsBack, date);
		datePicker.selectDate(monthsBack, date);
		
		WebElement datePickerBar = driver.findElementById("datepicker1");		
		String dateInBar = datePickerBar.getAttribute("value");
		
		
		try {
			assertEquals(dateInBar, dateString);
			
			test.log(LogStatus.PASS, "Correct date is displayed");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
	}
	
	@Test
	public void testMenu() {
		test = extent.startTest("Check if menu items change background color and have correct href");
		List<WebElement> elementList = new ArrayList<WebElement>();
		String hex;
		String expectedHex = "#ff9900";
		String href;
		String expectedHref = "#";
		driver.manage().window().maximize();
		Actions builder = new Actions(driver);
		String url = "http://demoqa.com/menu/";
		driver.navigate().to(url);
		
		//Get all menu elements inside the box
		WebElement checkElement = driver.findElementById("tabs-1");
		elementList = checkElement.findElements(By.className("ui-corner-left"));
		
		//loop through elements checking for background change on hover and link working
		for(WebElement ele: elementList) {
			builder.moveToElement(ele).perform();
			hex = Color.fromString(ele.getCssValue("background-color").toString()).asHex();
			href = ele.findElement(By.tagName("a")).getAttribute("href");
			
			
			try {
				assertEquals(expectedHex, hex);
				
				test.log(LogStatus.PASS, "correct colour shown when hovered over");
				}catch(AssertionError e) {
					test.log(LogStatus.FAIL, "oof, what a goof");
					
					fail();
				}finally {
					test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
				extent.endTest(test);
				}
			
			
			try {
				assertEquals(url+expectedHref, href);
				
				test.log(LogStatus.PASS, "correct href shown when hovered over");
				}catch(AssertionError e) {
					test.log(LogStatus.FAIL, "oof, what a goof");
					
					fail();
				}finally {
					test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
				extent.endTest(test);
				}
			
		}
	}
	
	@Ignore
	private void moveBar(WebElement slider, int fraction) {
		Actions builder = new Actions(driver);
		int width = slider.getSize().getWidth();
		int widthPerOne = width/9;
		int pixelsMoved = widthPerOne*fraction;
		
		System.out.println(pixelsMoved);
		builder.clickAndHold().moveByOffset(pixelsMoved, 0).perform();
	}
	
	@Ignore
	private int getSliderValue(WebElement slider) {
		return Integer.parseInt(driver.findElement(By.id("amount1")).getAttribute("value"));
	}
	@Test
	public void testSlider() {
		test = extent.startTest("Check if slider can be dragged and gives correct values");
		Actions builder = new Actions(driver);
		String url = "http://demoqa.com/slider/";
		driver.navigate().to(url);
		
		WebElement slider = driver.findElement(By.id("slider-range-max"));
		WebElement amount = driver .findElement(By.id("amount1"));
		
		int width = slider.getSize().getWidth();
		int widthPerOne = width/9;
		
		//reset slider
		try {
			builder.moveToElement(slider).clickAndHold().moveByOffset(-widthPerOne*4, 0).release().perform();
			amount = driver .findElement(By.id("amount1"));
			assertEquals(1, getSliderValue(slider));
			
			test.log(LogStatus.PASS, "slider moved to correct start position");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}

		
		
		try {
			moveBar(slider, 5);
			assertEquals(6, getSliderValue(slider));
			try { Thread.sleep(1000); } catch(Exception e) {} 
			
			
			test.log(LogStatus.PASS, "slider moved forward correct amount");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
		

		
		try {
			moveBar(slider, 4);
			assertEquals(10, getSliderValue(slider));
			try { Thread.sleep(1000); } catch(Exception e) {} 
			
			
			test.log(LogStatus.PASS, "slider moved forward correct amount");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}

		try {
			moveBar(slider, -5);
			assertEquals(5, getSliderValue(slider));
			try { Thread.sleep(1000); } catch(Exception e) {}
			
			
			test.log(LogStatus.PASS, "slider moved backwards correct amount");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
		builder.release();
	}	
	

	@Test
	public void testTabs() {
		test = extent.startTest("Check if clicking tabs displays text");
		driver.manage().window().maximize();
		String url = "http://demoqa.com/tabs";
		String divId;
		driver.navigate().to(url);

		
		WebElement checkElement;
		List<WebElement> elementList = new ArrayList<WebElement>();
		
		elementList = driver.findElementsByClassName("ui-state-default");
		System.out.println("there are " + elementList.size()+ " tabs");
		for(WebElement ele: elementList) {
			ele.click();
			divId = ele.getAttribute("aria-controls");
			checkElement = driver.findElement(By.id(divId));
			
			
			try {
				assertTrue(checkElement.isDisplayed());
				try { Thread.sleep(1000); } catch(Exception e) {}
				
				
				test.log(LogStatus.PASS, "tab info displayed when tab is clicked");
				}catch(AssertionError e) {
					test.log(LogStatus.FAIL, "oof, what a goof");
					
					fail();
				}finally {
					test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
				extent.endTest(test);
				}
		}
	}
	
	@Test
	public void testTooltips() {
		test = extent.startTest("Check if tooltips appear on hover");
		Actions builder = new Actions(driver);
		driver.manage().window().maximize();
		String url = "http://demoqa.com/tooltip";
		driver.navigate().to(url);
		
		WebElement inputBox = driver.findElement(By.id("age"));
		builder.moveToElement(inputBox).perform();
		
		WebElement toolTip = driver.findElementByClassName("ui-tooltip");

		
		try {
			assertTrue(toolTip.isDisplayed());
			try { Thread.sleep(1000); } catch(Exception e) {}
			
			
			test.log(LogStatus.PASS, "tooltip is displayed on hover");
			}catch(AssertionError e) {
				test.log(LogStatus.FAIL, "oof, what a goof");
				
				fail();
			}finally {
				test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test);
			}
		
	}
}
	
