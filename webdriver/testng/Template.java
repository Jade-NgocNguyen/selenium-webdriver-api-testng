package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Template {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
