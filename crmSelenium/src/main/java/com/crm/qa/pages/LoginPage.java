package com.crm.qa.pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;

public class LoginPage extends BaseTest {

	// Page Factory - Object Rep:
	
		
		@FindBy(name = "username")
		WebElement username;
		
		@FindBy(name = "password")
		WebElement password;
		
		@FindBy(xpath = "//input[@value='Login']")
		WebElement loginBtn;
		
		@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
		WebElement signUpBtn;
		
		@FindBy(xpath = "//img[@class='img-responsive']")
		WebElement crmLogo;
		
		//Initializing page objects
		public LoginPage()
		{
				PageFactory.initElements(driver, this);
		}

		public String validateLoginPageTitle()
		{
			return driver.getTitle();
		}
		
		public boolean validateCRMImage()
		{
			return crmLogo.isDisplayed();
		}
		
		public HomePage Login(String un, String pw)
		{
			username.sendKeys(un);
			password.sendKeys(pw);
			loginBtn.click();
			return new HomePage();
		}
	}
