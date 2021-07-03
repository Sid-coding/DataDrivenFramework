package com.w2a.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestProperties {
	
	public static void main(String[] args) throws IOException {
		String directory = System.getProperty("user.dir");
		
		
		Properties config = new Properties();
		Properties OR = new Properties();
		FileInputStream fis = new FileInputStream(directory +"\\src\\test\\resrources\\properties\\Config.properties");
		config.load(fis);
		
		fis = new FileInputStream(directory +"\\src\\test\\resrources\\properties\\OR.properties");
		OR.load(fis);
		System.out.println(config.getProperty("browser"));
		System.out.println(OR.getProperty("bmlBtn"));
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager");
		
	}

}
