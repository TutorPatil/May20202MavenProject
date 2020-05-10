package com.selenium.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.common.io.Files;

public class BaseClass {

	public static WebDriver driver = null;
	
	//***************************************************************************************
	
	/**
	 * Title This method is for reading the config Data
	 * @author Patil
	 * @param keyname
	 * @return PropertyValue
	 * @throws Exception
	 */
	
	public static String getConfigData(String keyname) throws Exception {
		String value = "";

		File f = new File("./data/config.properties");
		FileInputStream fis = new FileInputStream(f);

		Properties prop = new Properties();
		prop.load(fis);

		value = prop.getProperty(keyname);

		return value;

	}
	
	//***************************************************************************************

	/**
	 * Title : - This method is to launch the Actitime Application
	 * @author Patil
	 * @throws Exception
	 * @param none
	 */
	
	@BeforeMethod(alwaysRun=true)
	public static void launchActitime() throws Exception {
		
		System.out.println("Before Method....Inside before Method");

		String browser = getConfigData("browser");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			System.setProperty("webdriver.chrome.driver", "./tools/chromedriver.exe");
			driver = new ChromeDriver();

		}

		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "tools/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(getConfigData("url"));
	}
	
	//***************************************************************************************

	/**
	 * Title : - This method is to close the Actitime Application
	 * @author Patil
	 * @throws none
	 * @param none
	 * @return none
	 * 
	 */
	
	@AfterMethod(alwaysRun = true)
	public static void closeBrowser() {
		System.out.println("After Method....Inside After Method");
		driver.close();
	}
	
	//***************************************************************************************
	
	/**
	 * Title : - This method is to write results to a txt file
	 * @author Patil
	 * @throws Exception
	 * @Param testCaseName, status
	 * @return none
	 * 
	 */

	
	public static void writeResultsToFile(String testCaseName, String status) throws Exception {
		File f = new File("./results/results.txt");
		FileWriter fw = new FileWriter(f, true);

		fw.write(testCaseName + "-----" + status+"\n");

		fw.flush();
		fw.close();

	}
	
	//***************************************************************************************
	
	/**
	 * Title : - This method is to capture  the screenshots of failed tests
	 * @author Patil
	 * @throws Exception
	 * @Param fileName
	 * @return none
	 * 
	 */

	public static void captureScreenShot(String fileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./results/screenshots/" + fileName + ".png");
		Files.copy(src, dest);

	}

	//***************************************************************************************
	/**
	 * Title : - This method is to fetch locatordata and test data from excel file
	 * @author Patil
	 * @throws Exception
	 * @Param fileName,pageName,elementName
	 * @return locator or Test data
	 * 
	 */
	
	
	public static String getDataFromExcelFile(String fileName, String pageName, String elementName) throws Exception {
		String locator = "";
		File file = null;

		if (fileName.contains("locator")) {
			file = new File("./data/locatordata.xlsx");
		} else if (fileName.contains("test")) {
			file = new File("./data/testdata.xlsx");
		}
		FileInputStream fio = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(fio);

		XSSFSheet worksheet = workbook.getSheet("Sheet1");

		int rows = worksheet.getLastRowNum();
		// rows = rows+1;

		for (int x = 1; x <= rows; x++) {

			String page = worksheet.getRow(x).getCell(0).getStringCellValue();
			String loc = worksheet.getRow(x).getCell(1).getStringCellValue();

			if ((pageName.equals(page)) && (elementName.equals(loc))) {
				locator = worksheet.getRow(x).getCell(2).getStringCellValue();
				break;
			}
		}
		workbook.close();

		return locator;

	}
	
	//***************************************************************************************
	
	@BeforeSuite
	public static void beforeSuite()
	{
		System.out.println(" Beore Suite....This method will run first before all other methods");
	}
	
	@AfterSuite
	public static void afterSuite()
	{
		System.out.println(" After Suite....This method will run after all other methods at the end");
	}
	
	
	@BeforeTest
	public static void beforetest()
	{
		System.out.println(" Beore Test....This method will run before every testngxml test");
	}
	
	@AfterTest
	public static void aftertest()
	{
		System.out.println(" After Test....This method will run after every testngxml test");
	}
	
	

	@BeforeClass
	public static void beforeClass()
	{
		System.out.println(" Beore Class....This method will run before every class");
	}
	
	@AfterClass
	public static void afterClass()
	{
		System.out.println(" After Class....This method will run after every class");
	}
	
	
	
}
