package com.crm.qa.base;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.crm.qa.ExtentReportListener.ExtentReportsUtility;
import com.crm.qa.util.Constants;
import com.crm.qa.util.TestUtil;

@Listeners(com.crm.qa.util.TestEventListnersutility.class)

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	// public static EventFiringWebDriver e_driver;
	public static ExtentReportsUtility extentReportsUtility = ExtentReportsUtility.getInstance();

	public BaseTest() {
		try {
			prop = new Properties();

			FileInputStream ip = new FileInputStream(Constants.APPLICATION_PROPERTIES);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@BeforeMethod
    public void setUp() {
        initialization();
		/*
		 * extentReportsUtility = ExtentReportsUtility.getInstance();
		 * extentReportsUtility.startExtentReport();
		 */
    }

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); // create util class
																									// for timeunit
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
		extentReportsUtility = ExtentReportsUtility.getInstance();
		extentReportsUtility.startExtentReport();
	}

	@AfterMethod
	public void tearDown() {
		extentReportsUtility.endReport();
		driver.quit();
	}

	public WebDriver returnDriverInstance() {
		return driver;
	}

}
