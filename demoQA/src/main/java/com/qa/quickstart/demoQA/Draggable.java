package com.qa.quickstart.demoQA;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Draggable {
	@FindBy(id="draggable")
	private WebElement draggableBox;
	
	public void moveBox() {
		Point coordinates = draggableBox.getLocation();
		
		try {
		Robot robot = new Robot();
		robot.mouseMove(coordinates.getX()+50 ,coordinates.getY()+120);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(coordinates.getX() +300, coordinates.getY()+300);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}catch(Exception e){}
		
	}
}
