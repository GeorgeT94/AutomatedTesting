package com.qa.quickstart.WebDriver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTest{
	ChromeDriver driver;

	@Before
	public void before() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Testing\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	@Test
	public void test1() {
		driver.manage().window().maximize();
		String url = "http://thedemosite.co.uk/addauser.php";
		driver.navigate().to(url);
		WebElement checkElement = driver.findElementByName("username");
		checkElement.click();
		checkElement.sendKeys("George");
		
		checkElement = driver.findElementByName("password");
		checkElement.click();
		checkElement.sendKeys("mypassword");
		
		checkElement = driver.findElementByName("FormsButton2");
		checkElement.click();
		
		url = "http://thedemosite.co.uk/login.php";
		driver.navigate().to(url);
		
		checkElement = driver.findElementByName("username");
		checkElement.click();
		
		checkElement.sendKeys("George");
		
		checkElement = driver.findElementByName("password");
		checkElement.click();
		checkElement.sendKeys("mypassword");
		
		checkElement = driver.findElementByName("FormsButton2");
		checkElement.click();
		
		checkElement = driver.findElementByXPath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b");
		String status = checkElement.getText();
		System.out.println(status);

		assertEquals("**Successful Login**", status);
	}	
		
}
