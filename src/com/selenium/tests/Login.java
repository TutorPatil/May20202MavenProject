package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.selenium.base.BaseClass;
import com.selenium.utils.Commonutils;

public class Login extends BaseClass {
		

	// @Test(groups = {"smoke", "Regression","login","login_001"} )
	
	//@Parameters({ "username","password","age" })
	
	@Test(dataProvider = "logndataExcel",dataProviderClass = com.selenium.dataproviders.DataProviders.class)	
	public static void login_001(String  user, String pass) throws Exception {
				
		
		Commonutils.loginToActitime(user, pass);

		boolean result = driver.findElement(By.xpath(getDataFromExcelFile("locator", "Home", "Logout_Link")))
				.isDisplayed();
		
		Assert.assertTrue(result, "Could not login...login_001 Failed");
		writeResultsToFile("login_001", "Pass");				
	}	
	
	
	

	
	 //@Test(groups = {"smoke","Regression","login","login_002"} )
	 @Test
	public static void login_002() throws Exception {
		
		String userName = getDataFromExcelFile("test", "Login", "UserName_TextBox");
		String password = getDataFromExcelFile("test", "Login", "Password_Invalid_TextBox");

		
		Commonutils.loginToActitime(userName, password);

		boolean result = driver.findElement(By.xpath(getDataFromExcelFile("locator", "Login", "ErrorMsg_Text")))
				.isDisplayed();
		
		Assert.assertTrue(result, " Login_002 Failed...");
		writeResultsToFile("login_003", "Pass");
		
	}
	
		//@Test
		public static void softAssertExample() throws Exception {
			
			SoftAssert softAssert = new SoftAssert();
			
			boolean res = (10 > 5);
			
			softAssert.assertTrue(res);
			
			System.out.println(" after first assert ");
			
			//Assert.assertFalse(res);
			
			softAssert.assertFalse(res);
			
			System.out.println("After second assert");
			
			int x = 5;
			int y = 6;
			
			softAssert.assertEquals(x,y);
			
			System.out.println("printing all the assertions");
			
			softAssert.assertAll();
			
			

		}	
		
	
	

	
	
	
	
	
	
	
	//@Test
	public static void login_003()  {
		boolean result = false;	
		try {
			
		
		String userName = getDataFromExcelFile("test", "Login", "UserName_Invalid_TextBox");
		String password = getDataFromExcelFile("test", "Login", "Password_TextBox");

		
		Commonutils.loginToActitime(userName, password);
		result = driver.findElement(By.xpath(getDataFromExcelFile("locator", "Login", "ErrorMsg_Text")))
				.isDisplayed();
		
		Assert.assertTrue(result, "Could not login...login_002 Failed");
		writeResultsToFile("login_002", "Pass");
		}
		catch(Exception ne)
		{
			System.out.println("The error message was not seen");	
			ne.printStackTrace();			
			try {
				Assert.assertTrue(result, "Could not login...login_002 Failed");
				writeResultsToFile("login_002", "Fail");
				captureScreenShot("login_002");
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		
	}
	

}
