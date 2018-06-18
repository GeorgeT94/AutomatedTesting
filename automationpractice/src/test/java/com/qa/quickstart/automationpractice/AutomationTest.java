package com.qa.quickstart.automationpractice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationTest {
	ChromeDriver driver;

	@Before
	public void before() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Testing\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown() {
		//driver.quit();
	}
	@Test
	public void test1() {
		String url = "http://automationpractice.com/index.php";
		String searchWord = "dress";
		String specificItem = "Printed Summer Dress";
		List<String> clothesList = new ArrayList<String>();
		driver.navigate().to(url);
		WebElement checkElement;
		List<WebElement> checkList;
		checkElement = driver.findElementById("search_query_top");
		checkElement.click();
		checkElement.sendKeys(searchWord);
		
		checkElement = driver.findElementById("searchbox");
		checkElement.submit();
		
		checkList = driver.findElementsByClassName("product-name");
		for (WebElement element : checkList) {
			clothesList.add(element.getText());
			System.out.println(element.getText());
		}
		assertTrue(clothesList.contains(specificItem));

	}
	

}
