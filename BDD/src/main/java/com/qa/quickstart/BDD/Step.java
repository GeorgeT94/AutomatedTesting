package com.qa.quickstart.BDD;

import static org.junit.Assert.*;

import org.junit.AfterClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;

public class Step {
	ChromeDriver driver;
	static ExtentReports extent;
	static ExtentTest test;
	
	
	@Before
	public void before() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Testing\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {
		driver.manage().window().maximize();
		String url = Constant.URL1;
		driver.navigate().to(url);
		assertEquals("http://www.practiceselenium.com/welcome.html", driver.getCurrentUrl());
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
		Actions action = new Actions(driver);
	    WebElement menu = driver.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]"));
	    action.moveToElement(menu).click().perform();
	    
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
		 WebElement tea1 = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453230000\"]"));
		 WebElement tea2 = driver.findElement(By.xpath("//*[@id=\"wsb-element-00000000-0000-0000-0000-000453231072\"]"));
		 
		 assertTrue("tea is not displayed", tea1.isDisplayed());
		 assertTrue("tea is not displayed", tea2.isDisplayed());
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		 Actions action = new Actions(driver);
		 WebElement checkOut = driver.findElement(By.xpath("//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[5]"));
		 action.moveToElement(checkOut).click().perform();
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		assertEquals("http://www.practiceselenium.com/check-out.html", driver.getCurrentUrl());
	}
}
