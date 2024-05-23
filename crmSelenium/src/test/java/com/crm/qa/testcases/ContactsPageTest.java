package com.crm.qa.testcases;

import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;

import com.crm.qa.ExtentReportListener.ExtentReportsUtility;
import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends BaseTest {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	ExtentReportsUtility extentReportsUtility = ExtentReportsUtility.getInstance();

	String sheetName = "contacts";

	private static final Logger log = Logger.getLogger(ContactsPageTest.class);

	public ContactsPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		// contactsPage = homePage.clickOnContactsLink();
	}

	@BeforeMethod
	public void navigateToContactsPage() {
		if (driver != null) {
			try {
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainpanel");
				//System.out.println("Frame 'mainpanel' is found.");

				contactsPage = homePage.clickOnContactsLink();
			} catch (NoSuchFrameException e) {
				System.out.println("Frame 'mainpanel' not found.");
			}
		} else {
			System.out.println("WebDriver instance is not initialized.");
		}
	}

	@Test(priority = 1)
	public void verifyContactsPageLabel() {
		extentReportsUtility.logTestInfo("Verifying Contacts Page Label");

		log.info("Verifying contacts page label.");
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is missing on the page");
	}

	@Test(priority = 2)
	public void selectSingleContactsTest() {
		extentReportsUtility.logTestInfo("Selecting a single contact");

		log.info("Selecting single contact.");
		contactsPage.selectContactsByName("Jan Bhar");

	}

	@Test(priority = 3)
	public void selectMultipleContactsTest() {
		log.info("Selecting multiple contacts.");
		extentReportsUtility.logTestInfo("Selecting multiple contacts");
		contactsPage.selectContactsByName("abc xyz");
		contactsPage.selectContactsByName("Jan Bhar");
	}

	@DataProvider
	public Object[][] getCRMTestData() {
		log.info("Fetching test data from Excel sheet: " + sheetName);
		// return TestUtil.getTestData(sheetName);
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company) {
		extentReportsUtility.logTestInfo("Creating a new contact: " + title + " " + firstName + " " + lastName);
		log.info("Creating a new contact: " + firstName + " " + lastName);
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, firstName, lastName, company);
	}

	@AfterClass
	public void tearDown() {
		log.info("Closing the browser.");
		driver.quit();
	}
}
