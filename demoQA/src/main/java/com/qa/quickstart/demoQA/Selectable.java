package com.qa.quickstart.demoQA;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Selectable {

	@FindBy(id="selectable")
	private WebElement selectBox;
	
	@FindBy(className="ui-selected")
	private List<WebElement> elementList; 
	
	public void selectBoxes(){
		Point coordinates = selectBox.getLocation();
		
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

	
	}
	
	public List<WebElement> selectedBoxes() {
		return elementList;
	}

}
