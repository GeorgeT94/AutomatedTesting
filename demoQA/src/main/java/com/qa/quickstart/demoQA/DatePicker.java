package com.qa.quickstart.demoQA;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DatePicker {
	WebDriver driver;
	
	@FindBy(id="datepicker1")
	WebElement datePickerBar;
	
	@FindBy(tagName="td")
	List<WebElement> elementList;
	
	
	public DatePicker(WebDriver driver) {
		this.driver = driver;
	}
	
	private void pressButton(String className, int count) {
		WebElement btn;
		for(int i = 0; i < count; i++ ){
			btn = driver.findElement(By.className(className));
			try { Thread.sleep(200); } catch(Exception e) {} ;
			btn.click();
		}
		
		
	}
	
	public String getDateString(int monthsBack,int date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMMMM dd, yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		Date currentDate = new Date();
		LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date expectedDate;
		
		int month = localDate.getMonthValue() - monthsBack;
		
		try {
			expectedDate = format1.parse(date +"-" + month + "-2018");
		}catch(Exception e) {
			expectedDate = currentDate;
		}
		
		String dateString = formatter.format(expectedDate);
		return dateString;
	}
	
	public void selectDate(int monthsBack, int date) {

		
		pressButton("ui-datepicker-prev", monthsBack);
		
		for(WebElement ele: elementList){
			if(ele.getText().equals(Integer.toString(date))){
				ele.click();
			}
		}
		try { Thread.sleep(1000); } catch(Exception e) {} ;
		
	}
	

	
	public void clickDateBox() {
		datePickerBar.click();
		try { Thread.sleep(500); } catch(Exception e) {} ;
	}
}
