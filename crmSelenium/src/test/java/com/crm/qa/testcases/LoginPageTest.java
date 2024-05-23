package com.crm.qa.testcases;

import org.testng.Assert;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.ExtentReportListener.ExtentReportsUtility;
import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

//LoginPage

public class LoginPageTest extends BaseTest {
	LoginPage loginPage;
	HomePage homePage;
	ExtentReportsUtility extentReportsUtility = ExtentReportsUtility.getInstance();

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		extentReportsUtility.startSingleTestReport("Login Page Title Test");

		String title = loginPage.validateLoginPageTitle();
		extentReportsUtility.logTestInfo("Validating login page title: " + title);
		Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, and support.");
		
	}

	@Test(priority = 2)
	public void crmLogoImageTest() {
		extentReportsUtility.startSingleTestReport("CRM Logo Image Test");

		boolean flag = loginPage.validateCRMImage();
		extentReportsUtility.logTestInfo("Validating CRM logo image");

		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void loginTest() {
		extentReportsUtility.startSingleTestReport("Login Test");

		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));// login method returns
																								// homepage class object
		extentReportsUtility.logTestInfo("Logging in with username: " + prop.getProperty("username"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}