package com.qa.quickstart.demoQA;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutoComplete {

	@FindBy(id="tagss")
	WebElement searchBar;
	
	@FindBy(className="ui-menu-item")
	List<WebElement> elementList;
	
	public List<String> getDropDownList(String searchTerm){
		List<String> autoCompleteList = new ArrayList<String>();
		searchBar.click();
		searchBar.clear();
		searchBar.sendKeys(searchTerm);
		
		try { Thread.sleep(500); } catch(Exception e) {}
		
		
		for (WebElement element : elementList) {
			autoCompleteList.add(element.getText());
		}
		return autoCompleteList;
	}
}
