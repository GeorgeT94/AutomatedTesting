package com.qa.quickstart.phpTravels;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PhpTravels {
	WebDriver driver;
	
	@FindBy(className="select2-input")
	WebElement searchBar;
	
	@FindBy(className="select2-drop")
	WebElement dropBar;
	
	@FindBy(css="html body div#select2-drop.select2-drop.select2-display-none.select2-with-searchbox.select2-drop-active ul.select2-results li.select2-results-dept-0.select2-result.select2-result-unselectable.select2-result-with-children ul.select2-result-sub li.select2-results-dept-1.select2-result.select2-result-selectable")
	WebElement firstCity;
	
	public PhpTravels(WebDriver driver) {
		this.driver = driver;
	}
	
	public void book(String city, int adults, int nights) throws AWTException {

		Robot robot = new Robot();

		Actions action = new Actions(driver);
		System.out.println(searchBar.getLocation());
		//action.moveToElement(searchBar);
		action.moveByOffset(595, 280).click().perform();
		
		action.sendKeys(city).perform();
		
		firstCity.click();
		action.moveToElement(searchBar);
		action.moveByOffset(300, 0).click().perform();
		

		try { Thread.sleep(2000); } catch(Exception e) {}
		System.out.println("testing..");
	}
}
