package com.pack.pageobjects;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

	public class BasePage {
		protected RemoteWebDriver driver;
		private By signInButton = By.linkText("Sign in");
		
		public BasePage(RemoteWebDriver driver) {
			this.driver = driver;
		}
		
		public SignInPage clickSignInBtn() {
			System.out.println("clicking on sign in button");
			WebElement signInBtnElement=driver.findElement(signInButton);
			if(signInBtnElement.isDisplayed()||signInBtnElement.isEnabled())
				signInBtnElement.click();
			else System.out.println("Element not found");
			return new SignInPage(driver);
		}
		
		public String getPageTitle(){
			String title = driver.getTitle();
			return title;
		}
		
		public boolean verifyBasePageTitle() {
			String expectedPageTitle="Google";
			return getPageTitle().contains(expectedPageTitle);
		}
	}

