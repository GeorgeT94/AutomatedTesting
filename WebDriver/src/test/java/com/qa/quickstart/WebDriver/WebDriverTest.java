package com.qa.quickstart.WebDriver;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebDriverTest{
	ChromeDriver driver;
	static ExtentReports extent;
	public static final String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\Admin\\Desktop\\UserLogins.xlsx";
	
	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Testing\\chromedriver.exe");
		extent= new ExtentReports("C:\\Users\\Admin\\Desktop\\demoSiteReport.html", true);
	}
	@Before
	public void before() {
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown() {
		driver.quit();
		extent.flush();
	}
	
	@Test
	public void testLogin() {
		ExtentTest test = extent.startTest("User signUp and login");
		
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


		try {
			assertEquals("**Successful Login**", status);
			test.log(LogStatus.PASS, "succesfully created an account and logged in");
		}catch(AssertionError e) {
			test.log(LogStatus.FAIL, "oof, what a goof");
			
			fail();
		}finally {
			test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
		extent.endTest(test);
		}
	}
	
	@Test
	public void testloginFromExcel() throws IOException, InvalidFormatException {
		String user = "default";
		String password = "password";
		ExtentTest test = extent.startTest("multiple User signUp and login");
		Login login = PageFactory.initElements(driver, Login.class);
		
		
		  // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
        
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
      
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                if(cell.getColumnIndex() == 0) {
                	user = dataFormatter.formatCellValue(cell);
                }
                if(cell.getColumnIndex() == 1) {
                	
                	password = dataFormatter.formatCellValue(cell);
                	
                	driver.manage().window().maximize();
            		String url = "http://thedemosite.co.uk/addauser.php";
            		driver.navigate().to(url);
            		System.out.println("user:" + user + ", password : " + password);
            		login.SignUpUser(user, password);
            		
            		url = "http://thedemosite.co.uk/login.php";
            		driver.navigate().to(url);
            		String status = login.logInUser(user, password);
            		
            		try {
            			assertEquals("**Successful Login**", status);
            			test.log(LogStatus.PASS, "succesfully created an account and logged in");
            		}catch(AssertionError e) {
            			test.log(LogStatus.FAIL, "oof, what a goof");
            			
            			//fail();
            		}finally {
            			test.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
            		extent.endTest(test);
            		}
                }
   
            }

        }
		
		
	}
		
}
