package com.qa.quickstart.demoQA;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Accordian {
	
	WebDriver driver;
	
	@FindBy(className="ui-accordion-header")
	List<WebElement> elementList;
	
	@FindBy(id="ui-id-2")
	WebElement tab2;
	
	@FindBy(id="ui-id-3")
	WebElement tab3;
	
	public Accordian(WebDriver driver) {
		this.driver = driver;
	}
	
	public void testAccordian() {
		
		WebElement checkElement;
		String divId;
		
		
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
			

			ele = driver.findElement(By.id(divId));
			
			assertTrue("text is still hidden", ele.getAttribute("aria-hidden").contains("false"));
			
			//go through the next tabs
			if(id == 11) tab2.click();
			if(id == 19)tab3.click();
			
		}
	}
}
