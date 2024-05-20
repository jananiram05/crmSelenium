package com.crm.qa.testcases;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import com.crm.qa.ExtentReportListener.ExtentReportsUtility;
import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


//HomeTest page
public class HomePageTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    TestUtil testUtil;
    ContactsPage contactsPage;
    ExtentReportsUtility extentReportsUtility = ExtentReportsUtility.getInstance();

    
    private static final Logger log = Logger.getLogger(HomePageTest.class);

    public HomePageTest() {
        super();
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();    
        testUtil = new TestUtil();
        loginPage = new LoginPage();
        contactsPage = new ContactsPage();
        log.info("Logging in with username: " + prop.getProperty("username"));
        homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
        // Verify if home page is successfully initialized
        if(homePage == null) {
            log.error("Home page initialization failed. Login might have failed.");
            Assert.fail("Home page initialization failed.");
        } else {
            log.info("Home page initialized successfully.");
        }
    }
    
    @Test(priority = 1)
    public void verifyHomePageTitleTest() {
        extentReportsUtility.startSingleTestReport("Verify Home Page Title Test");

        String homePageTitle = homePage.verifyHomePageTitle();
        log.info("Verifying home page title.");
        extentReportsUtility.logTestInfo("Home page title is: " + homePageTitle);

        Assert.assertEquals(homePageTitle, "CRMPRO", "Home page title not matched!!");
    }
    
    @Test(priority = 2)
    public void verifyUserNameTest() {
        extentReportsUtility.startSingleTestReport("Verify User Name Test");

        testUtil.switchToFrame();
        log.info("Verifying correct user name is displayed.");
        Assert.assertTrue(homePage.verifyCorrectUserName(), "Username is not displayed correctly!");
    }
    
    @Test(priority = 3)
    public void verifyContactsLinkTest() {
        extentReportsUtility.startSingleTestReport("Verify Contacts Link Test");

        testUtil.switchToFrame();
        log.info("Clicking on Contacts link.");
        contactsPage = homePage.clickOnContactsLink();
        extentReportsUtility.logTestInfo("Navigated to Contacts Page");

        Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is not displayed!");
    }
    
    @AfterMethod
    public void tearDown() {
        log.info("Closing the browser.");
        driver.quit();
    }
}
