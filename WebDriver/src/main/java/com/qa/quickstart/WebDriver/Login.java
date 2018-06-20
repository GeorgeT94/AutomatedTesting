package com.qa.quickstart.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Login {
	@FindBy(name="username")
	WebElement userInput;
	
	@FindBy(name="password")
	WebElement passInput;
	
	@FindBy(name="FormsButton2")
	WebElement submit;
	
	@FindBy(xpath="/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	WebElement status;
	
	
	
	public void SignUpUser(String user, String pass) {

		userInput.click();
		userInput.sendKeys(user);

		passInput.click();
		passInput.sendKeys(pass);
		
		submit.click();
	}

	public String logInUser(String user, String pass) {
		userInput.click();
		userInput.sendKeys(user);

		passInput.click();
		passInput.sendKeys(pass);
		
		submit.click();
		
		return status.getText();
	}
}
