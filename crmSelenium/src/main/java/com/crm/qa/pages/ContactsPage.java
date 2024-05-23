package com.crm.qa.pages;

import org.openqa.selenium.support.ui.Select;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;

public class ContactsPage extends BaseTest{
  
@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
    private static final Logger log = Logger.getLogger(ContactsPage.class);

	
	public ContactsPage(){
		PageFactory.initElements(driver, this);
        log.info("ContactsPage elements initialized.");

	}
	
	public boolean verifyContactsLabel(){
        boolean isDisplayed = contactsLabel.isDisplayed();

        log.info("Contacts label is displayed: " + isDisplayed);
		return contactsLabel.isDisplayed();

	}
	
	public void selectContactsByName(String name) {
        try {
            Thread.sleep(2000); 
            WebElement contactCheckbox = driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
                + "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));
            contactCheckbox.click();
            log.info("Selected contact by name: " + name);
        } catch (Exception e) {
            log.error("Error selecting contact by name: " + name, e);
        }
    }
	
	// use when Data driven is not added to project
	 public void createNewContact(String title, String ftName, String ltName, String comp) {
	        try {
	            Select select = new Select(driver.findElement(By.name("title")));
	            select.selectByVisibleText(title);
	            log.info("Selected title: " + title);
	            
	            firstName.sendKeys(ftName);
	            log.info("Entered first name: " + ftName);
	            
	            lastName.sendKeys(ltName);
	            log.info("Entered last name: " + ltName);
	            
	            company.sendKeys(comp);
	            log.info("Entered company: " + comp);
	            
	            saveBtn.click();
	            log.info("Clicked on save button.");
	        } catch (Exception e) {
	            log.error("Error creating new contact", e);
	        }
	    }
	

}
