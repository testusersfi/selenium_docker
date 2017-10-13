package com.pack.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.pageobjects.BasePage;

public class BasePageTest extends TestBaseSetup {

	private RemoteWebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("Entered before class");
		driver = getDriver();
		System.out.println("driver initialization is complete ");
	}

	@Test
	public void verifyHomePage() {
		System.out.println("Home page test...");
		BasePage basePage = new BasePage(driver);
		Assert.assertTrue(basePage.verifyBasePageTitle(), "Home page title doesn't match");
	}

}
