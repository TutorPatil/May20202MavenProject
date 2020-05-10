package com.selenium.dataproviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name = "logndata")
	public static Object[][] supplyLoginData()
	{
		Object[][] obj = new Object[3][2];
		
		
		obj[0][0] = "admin";
		obj[0][1] = "manager";

		obj[1][0] = "admin";
		obj[1][1] = "manager";
		
		obj[2][0] = "admin";
		obj[2][1] = "manager";		
		return obj;		
	}
	
	@DataProvider(name = "logndataExcel")	
	public static Object[][] supplyLoginDataUsingExcel() throws IOException
	{		
		
		File file = new File("./data/testdata.xlsx");
		
		FileInputStream fio = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(fio);

		XSSFSheet worksheet = workbook.getSheet("logindata");

		int rows = worksheet.getLastRowNum();
		// rows = rows+1;
		
		Object[][] obj = new Object[rows+1][2];

		for (int x = 0; x <= rows; x++) {
			
			for(int y = 0; y < 2; y++)
			{
				obj[x][y] = worksheet.getRow(x).getCell(y).getStringCellValue();				
			}	 
		}
		workbook.close();
		return obj;
	}
	
	

}
