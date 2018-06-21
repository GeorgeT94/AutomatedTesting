package com.qa.quickstart.phpTravels;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhpTravels {
	WebDriver driver;
	
	@FindBy(className="select2-input")
	WebElement searchBar;
	
	@FindBy(className="select2-drop")
	WebElement dropBar;
	
	@FindBy(xpath="//*[@id=\"select2-drop\"]/ul/li/ul/li[1]")
	WebElement firstCity;
	
	@FindBy(name="checkin")
	WebElement checkIn;
	
	@FindBy(name="checkout")
	WebElement checkOut;
	
	@FindBy(className="datepicker-days")
	WebElement datePicker;
	
	@FindBy(className="day")
	List<WebElement> days;
	
	@FindBy(xpath="//*[@id=\"HOTELS\"]/form/div[5]/button")
	WebElement submitButton;
	
	@FindBy(xpath="//*[@id=\"body-section\"]/div[5]/div/div[3]/div[1]/div/table/tbody/tr/td/div[3]/a/button")
	WebElement detailsButton;
	
	@FindBy(xpath="//*[@id=\"ROOMS\"]/div/table/tbody/tr[1]/td/div[2]/div[2]/div/div[3]/div/button")
	WebElement bookButton;
	
	@FindBy(name="firstname")
	WebElement firstNameInput;
	
	@FindBy(name="lastname")
	WebElement lastNameInput;

	@FindBy(name="email")
	WebElement emailInput;

	@FindBy(name="confirmemail")
	WebElement confirmEmailInput;
	
	@FindBy(name="phone")
	WebElement phoneInput;
	
	@FindBy(name="address")
	WebElement addressInput;
	
	@FindBy(xpath="//*[@id=\"select2-drop\"]/div/input")
	WebElement countrySearch;
	
	@FindBy(name="guest")
	WebElement submitGuest;
	
	@FindBy(xpath="//*[@id=\"body-section\"]/div[1]/div[2]/div[2]/center/button[2]")
	WebElement payNow;
	
	@FindBy(name="gateway")
	WebElement selectPayment;
	
	@FindBy(xpath="//*[@id=\"gateway\"]/option[8]")
	WebElement jazzCash;
	
	@FindBy(xpath="//*[@id=\"frmTransaction\"]/input[14]")
	WebElement paymentButton;
	
	
	public PhpTravels(WebDriver driver) {
		this.driver = driver;
	}
	
	public void book(String city, int adults, int nights) {

		int startDay = 21;
		String firstName = "John";
		String lastName = "Doe";
		String email = "johndoe@gmail.com";
		String phoneNo = "07748833992";
		String address = "Buckingham palace, Westminster, London SW1A 1AA";
		String myCountry ="united kingdom";

		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		System.out.println(scrFile2.getAbsolutePath());
		
		action.moveByOffset(595, 280).click().perform();
		
		action.sendKeys(city).perform();
		action.moveByOffset(0, 50).perform();
		
		wait.until(ExpectedConditions.visibilityOf(firstCity));
		firstCity.click();
		action.moveByOffset(0, -50).perform();
		
		wait.until(ExpectedConditions.visibilityOf(checkIn));
		action.moveToElement(checkIn).click().perform();
		action.moveByOffset(0, 100).perform();
		
		
		wait.until(ExpectedConditions.visibilityOf(days.get(0)));
		for(WebElement day: days) {
			if(day.getText().equals(Integer.toString(startDay))) {
				day.click();
				break;
			}
		}
		
		action.moveToElement(checkIn).perform();
		action.moveToElement(checkOut).perform();
		
		try { Thread.sleep(300); } catch(Exception e) {} 
		for(WebElement day: days) {
			if(day.getText().equals(Integer.toString(startDay + nights))) {
				day.click();
				break;
			}
		}
		
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		action.moveToElement(submitButton).click().perform();
		
		wait.until(ExpectedConditions.visibilityOf(detailsButton));
		action.moveToElement(detailsButton).click().perform();
		jse.executeScript("window.scrollBy(0,500)", "");
		wait.until(ExpectedConditions.visibilityOf(bookButton));
		action.moveToElement(bookButton).click().perform();
		
		
		wait.until(ExpectedConditions.visibilityOf(firstNameInput));
		action.moveToElement(firstNameInput).click().sendKeys(firstName).perform();
		action.moveToElement(lastNameInput).click().sendKeys(lastName).perform();
		
		action.moveToElement(emailInput).click().sendKeys(email).perform();
		action.moveToElement(confirmEmailInput).click().sendKeys(email).perform();
		
		action.moveToElement(phoneInput).click().sendKeys(phoneNo).perform();
		action.moveToElement(addressInput).click().sendKeys(address).perform();
		
		
		action.moveByOffset(0, 50).click().sendKeys(myCountry).sendKeys(Keys.RETURN).perform();
		

		action.moveToElement(submitGuest).click().perform();
		
		wait.until(ExpectedConditions.visibilityOf(payNow));
		action.moveToElement(payNow).click().perform();
		
		wait.until(ExpectedConditions.visibilityOf(selectPayment));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		System.out.println(scrFile.getAbsolutePath());
		
		action.moveToElement(selectPayment).click().perform();
		
		try { Thread.sleep(1000); } catch(Exception e) {} 
		action.sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.RETURN).perform();
		
		wait.until(ExpectedConditions.visibilityOf(paymentButton));
		action.moveToElement(paymentButton).click().perform();
	}
}
