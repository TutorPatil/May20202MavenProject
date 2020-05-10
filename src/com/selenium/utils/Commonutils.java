package com.selenium.utils;

import java.io.IOException;

import org.openqa.selenium.By;

import com.selenium.base.BaseClass;

public class Commonutils extends BaseClass{
	
	
	public static void loginToActitime(String userName , String password) throws Exception
	{
		
		driver.findElement(By.xpath(getDataFromExcelFile("locator","Login", "UserName_TextBox")))
		.sendKeys(userName);
		
		driver.findElement(By.xpath(getDataFromExcelFile("locator","Login", "Password_TextBox"))).
		sendKeys(password);
		
		driver.findElement(By.xpath(getDataFromExcelFile("locator","Login", "Ok_Button"))).click();
		
			
	}
	
	
	public static boolean loginToActitime() throws Exception
	{
		
		driver.findElement(By.xpath(getDataFromExcelFile("locator","Login", "UserName_TextBox")))
		.sendKeys(getDataFromExcelFile("test", "Login", "UserName_TextBox"));
		
		driver.findElement(By.xpath(getDataFromExcelFile("locator","Login", "Password_TextBox"))).
		sendKeys(getDataFromExcelFile("test", "Login", "Password_TextBox"));
		
		driver.findElement(By.xpath(getDataFromExcelFile("locator","Login", "Ok_Button"))).click();
		
		boolean result = driver.findElement(By.xpath(getDataFromExcelFile("locator", "Home", "Logout_Link")))
				.isDisplayed();
		
		if(result)
			return true;
		else
			return false;
				
			
	}

}
