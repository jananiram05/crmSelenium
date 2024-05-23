package com.crm.qa.pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

import com.crm.qa.base.BaseTest;

public class HomePage extends BaseTest {
    
    @FindBy(xpath = "//td[contains(text(),'User: jan bhar')]")
    @CacheLookup // This annotation will store the element in cache, instead of looking from the page, for better performance of framework
    WebElement userNameLabel;
    
    @FindBy(xpath = "//a[contains(text(),'Contacts')]")
    WebElement contactsLink;
    
    @FindBy(xpath = "//a[contains(text(),'New Contact')]")
    WebElement newContactLink;
    
    @FindBy(xpath = "//a[contains(text(),'Deals')]")
    WebElement dealsLink;
    
    @FindBy(xpath = "//a[contains(text(),'Tasks')]")
    WebElement tasksLink;
    
    private static final Logger log = Logger.getLogger(HomePage.class);

    public HomePage() {
        PageFactory.initElements(driver, this); 
        log.info("HomePage elements initialized.");
    }
    
    public String verifyHomePageTitle() {
        String title = driver.getTitle();
        log.info("Home page title is: " + title);
        return title;
    }
    
    public boolean verifyCorrectUserName() {
        boolean isDisplayed = userNameLabel.isDisplayed();
        log.info("User name label is displayed: " + isDisplayed);
        return isDisplayed;
    }
    
    public ContactsPage clickOnContactsLink() {
        try {
            contactsLink.click();
            log.info("Clicked on Contacts link.");
        } catch (Exception e) {
            log.error("Unable to click on Contacts link.", e);
        }
        return new ContactsPage();
    }
    
    public DealsPage clickOnDealsLink() {
        try {
            dealsLink.click();
            log.info("Clicked on Deals link.");
        } catch (Exception e) {
            log.error("Unable to click on Deals link.", e);
        }
        return new DealsPage();
    }
    
    public TasksPage clickOnTasksLink() {
        try {
            tasksLink.click();
            log.info("Clicked on Tasks link.");
        } catch (Exception e) {
            log.error("Unable to click on Tasks link.", e);
        }
        return new TasksPage();
    }
    
    public void clickOnNewContactLink() {
        try {
            Actions action = new Actions(driver); 
            action.moveToElement(contactsLink).build().perform();
            log.info("Hovered over Contacts link.");
            newContactLink.click();
            log.info("Clicked on New Contact link.");
        } catch (Exception e) {
            log.error("Unable to click on New Contact link.", e);
        }
    }
}
