package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() throws InterruptedException {
		log.debug("inside login class");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(3000);
		
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "login is not successfull");
		log.debug("Login test case is done");
		
		
		
		
	}

}
