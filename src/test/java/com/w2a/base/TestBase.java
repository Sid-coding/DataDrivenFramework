package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.w2a.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	/*
	 * WebDriver -> Done
	 * Properties -> Done
	 * Logs - log4j jar, log4j.properties file
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 * ReportNG, ExtentReports
	 * Jenkins
	 */

	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	String directory = System.getProperty("user.dir");
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Logger log1 = Logger.getRootLogger();
	public static ExcelReader excel = new ExcelReader("C:\\Users\\bssid\\eclipse-workspace\\DataDrivenFramework\\src\\test\\resrources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	
	
	@BeforeSuite
	public void setUp() {
		
		if (driver ==null) {
			
			try {
				fis = new FileInputStream(directory +"\\src\\test\\resrources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(directory +"\\src\\test\\resrources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		WebDriverManager.chromedriver().setup();
		if(config.getProperty("browser").equals("chrome")) {
			driver=new ChromeDriver();
			log.debug("chrome browser launched successfully");
		}
		else if(config.getProperty("browser").equals("firefox")) {
			driver=new FirefoxDriver();
		}
		
		else if(config.getProperty("browser").equals("IE")) {
			driver=new InternetExplorerDriver();
		}
		
		driver.get(config.getProperty("testsiteurl"));
		log.debug("navigated to :"+config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);
	}
	
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		} catch(NoSuchElementException e) {
			return false;
		}
	}
	
	
	
	@AfterSuite
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
			log.debug("Test is successful");
		}
	}
}

