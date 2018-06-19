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

import junit.framework.TestCase;

 

public class demoQaTests{
	ChromeDriver driver;

	@Before
	public void before() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Testing\\chromedriver.exe");
		driver = new ChromeDriver();
		
	 }
	
	@After
	public void tearDown() {
		try { Thread.sleep(500); } catch(Exception e) {} 
		driver.close();
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
		
		assertTrue("no position change", (finalCoords.getX() - coordinates.getX()) > 200 && (finalCoords.getY() - coordinates.getY()) > 100);
	}
	@Test
	public void testSelectable() {
		driver.manage().window().maximize();
		String url = "http://demoqa.com/selectable/";
		driver.navigate().to(url);
		List<WebElement> elementList = new ArrayList<WebElement>();
		
		Point coordinates = driver.findElement(By.id("selectable")).getLocation();
		try { Thread.sleep(500); } catch(Exception e) {} 
		try {
			
			Robot robot = new Robot();
			robot.mouseMove(coordinates.getX()+50 ,coordinates.getY()+120);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			try { Thread.sleep(1000); } catch(Exception e) {} 
			robot.mouseMove(coordinates.getX() +200, coordinates.getY()+500);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		}catch (Exception e) {
			//do nothing
		}

		elementList = driver.findElementsByClassName("ui-selected");
		
		assertTrue("not all elements selected", elementList.size() == 7);
	}
	
	@Test
	public void testAccordian() {
		driver.manage().window().maximize();
		String url = "http://demoqa.com/accordion/";
		String divId;
		driver.navigate().to(url);

		
		WebElement checkElement;
		List<WebElement> elementList = new ArrayList<WebElement>();
		
		elementList = driver.findElementsByClassName("ui-accordion-header");
		System.out.println("there are " + elementList.size()+ " headers");
		for(WebElement ele: elementList) {

			divId = ele.getAttribute("id");
			ele.click();
			
			assertTrue("accordian not made active", ele.getAttribute("class").contains("ui-accordion-header-active"));
			
			//get the id number from the end of the id
			divId = ele.getAttribute("id");
			divId = divId.replaceAll("[^?0-9]+", " "); 
			divId = divId.trim();
			
			//go to next element
			int id = Integer.parseInt(divId) + 1;
			divId = "ui-id-" + id;
			ele = driver.findElementById(divId);
			
			assertTrue("text is still hidden", ele.getAttribute("aria-hidden").contains("false"));
			
			//go through the next tabs
			if(id == 11) driver.findElementById("ui-id-2").click();
			if(id == 19) driver.findElementById("ui-id-3").click();
			
		}
	}
	
	@Ignore
	private List<String> getDropDownList(WebElement searchBar, String searchTerm){
		List<String> autoCompleteList = new ArrayList<String>();
		List<WebElement> elementList = new ArrayList<WebElement>();
		searchBar.click();
		searchBar.clear();
		searchBar.sendKeys(searchTerm);
		
		try { Thread.sleep(500); } catch(Exception e) {} 
		elementList = driver.findElementsByClassName("ui-menu-item");
		
		for (WebElement element : elementList) {
			autoCompleteList.add(element.getText());
		}
		return autoCompleteList;
	}
	
	@Test
	public void testAutoComplete() {
		driver.manage().window().maximize();
		String url = "http://demoqa.com/autocomplete/";
		driver.navigate().to(url);
		
		List<String> autoCompleteList = new ArrayList<String>();
		
		WebElement checkElement = driver.findElementById("tagss");

		autoCompleteList = getDropDownList(checkElement, "ja");
		assertTrue(autoCompleteList.contains("Java"));
		assertTrue(autoCompleteList.contains("JavaScript"));
		
		
		autoCompleteList = getDropDownList(checkElement, "sc");
		assertTrue(autoCompleteList.contains("Scala"));
		
		
	}
	
	@Ignore
	private void pressButton(String id, int count) {
		WebElement btn;
		for(int i = 0; i < count; i++ ){
			btn = driver.findElementByClassName(id);
			try { Thread.sleep(200); } catch(Exception e) {} ;
			btn.click();
		}
		
		
	}
	
	@Test
	public void testDatePicker() {
		//creating an expected date in the format expected
		SimpleDateFormat formatter = new SimpleDateFormat("MMMMM dd, yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		Date currentDate = new Date();
		LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date expectedDate;
		int monthsBack = 5;
		int date = 24;
		int month = localDate.getMonthValue() - monthsBack;
		
		try {
			expectedDate = format1.parse(date +"-" + month + "-2018");
		}catch(Exception e) {
			expectedDate = currentDate;
		}
		
		String dateString = formatter.format(expectedDate);
		System.out.println(dateString);
		
		List<WebElement> elementList = new ArrayList<WebElement>();
		
		
		//Navigating to the web page
		driver.manage().window().maximize();
		String url = "http://demoqa.com/datepicker/";
		driver.navigate().to(url);
		

		//clicking on the datepicker bar and checking if a datepicker ui is displayed
		WebElement datePickerBar = driver.findElementById("datepicker1");
		datePickerBar.click();
		try { Thread.sleep(500); } catch(Exception e) {} ;
		WebElement datePickerUI= driver.findElementById("ui-datepicker-div");
		System.out.println(datePickerUI);
		assertTrue("datepicker ui is not displayed", datePickerUI.isDisplayed());
		
		pressButton("ui-datepicker-prev", monthsBack);
		
		elementList = driver.findElementsByTagName("td");
		for(WebElement ele: elementList){
			if(ele.getText().equals(Integer.toString(date))){
				ele.click();
			}
		}
		try { Thread.sleep(1000); } catch(Exception e) {} ;
		
		datePickerBar = driver.findElementById("datepicker1");		
		String dateInBar = datePickerBar.getAttribute("value");

		assertEquals(dateInBar, dateString);

	}
	
	@Test
	public void testMenu() {
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
			
			assertEquals(expectedHex, hex);
			assertEquals(url+expectedHref, href);
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
		Actions builder = new Actions(driver);
		String url = "http://demoqa.com/slider/";
		driver.navigate().to(url);
		
		WebElement slider = driver.findElement(By.id("slider-range-max"));
		WebElement amount = driver .findElement(By.id("amount1"));
		
		int width = slider.getSize().getWidth();
		int widthPerOne = width/9;
		
		//reset slider
		builder.moveToElement(slider).clickAndHold().moveByOffset(-widthPerOne*4, 0).release().perform();
		amount = driver .findElement(By.id("amount1"));
		assertEquals(1, getSliderValue(slider));
		
		moveBar(slider, 5);
		assertEquals(6, getSliderValue(slider));
		try { Thread.sleep(1000); } catch(Exception e) {} 
		
		moveBar(slider, 4);
		assertEquals(10, getSliderValue(slider));
		try { Thread.sleep(1000); } catch(Exception e) {} 
		
		moveBar(slider, -5);
		assertEquals(5, getSliderValue(slider));
		try { Thread.sleep(1000); } catch(Exception e) {}
		
		builder.release();
	}	
	

	@Test
	public void testTabs() {
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
			assertTrue(checkElement.isDisplayed());
		}
	}
	
	@Test
	public void testTooltips() {
		Actions builder = new Actions(driver);
		driver.manage().window().maximize();
		String url = "http://demoqa.com/tooltip";
		driver.navigate().to(url);
		
		WebElement inputBox = driver.findElement(By.id("age"));
		builder.moveToElement(inputBox).perform();
		
		WebElement toolTip = driver.findElementByClassName("ui-tooltip");
		assertTrue(toolTip.isDisplayed());
		
	}
}
	
