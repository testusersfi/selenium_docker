package com.pack.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseSetup {

	private RemoteWebDriver driver;
	//static String driverPath = "./driver/linux/";

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) throws MalformedURLException {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static RemoteWebDriver initChromeDriver(String appURL) throws MalformedURLException {
		System.out.println("Launching google chrome with new profile..");
		//System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		RemoteWebDriver driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                DesiredCapabilities.chrome());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static RemoteWebDriver initFirefoxDriver(String appURL) throws MalformedURLException {
		System.out.println("Launching Firefox browser..");
		//WebDriver driver = new FirefoxDriver();
		RemoteWebDriver driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                DesiredCapabilities.firefox());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
