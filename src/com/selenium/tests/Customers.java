package com.selenium.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.base.BaseClass;
import com.selenium.utils.Commonutils;

public class Customers extends BaseClass {
	
	//@Test(groups = {"smoke","custouer","customer_001"} )
	//@Test
	public static void customer_001() throws Exception {
		
		String userName = getDataFromExcelFile("test", "Login", "UserName_TextBox");
		String password = getDataFromExcelFile("test", "Login", "Password_TextBox");

		
		Commonutils.loginToActitime(userName, password);

		boolean result = driver.findElement(By.xpath(getDataFromExcelFile("locator", "Home", "Logout_Link")))
				.isDisplayed();
		
		Assert.assertTrue(result, "Could not login...customer_001 Failed");
		writeResultsToFile("customer_001", "Pass");
		
	}
	
	

}
