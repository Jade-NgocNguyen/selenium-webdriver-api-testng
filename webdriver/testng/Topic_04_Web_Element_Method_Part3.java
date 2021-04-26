package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Element_Method_Part3 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_CheckElementDislayed() {
		WebElement email = driver.findElement(By.xpath(""));
		WebElement education = driver.findElement(By.xpath(""));
		WebElement age = driver.findElement(By.xpath(""));
		WebElement name = driver.findElement(By.xpath(""));
		
		Assert.assertTrue(isDisplayed(email));
		Assert.assertTrue(isDisplayed(education));
		Assert.assertTrue(isDisplayed(age));
		Assert.assertFalse(isDisplayed(name));
		
		if(isDisplayed(email)) {
			email.sendKeys("Automation Testing");
			System.out.println("is Enabled");
		}else {
			System.out.println("is Disabled");
		}
		
		if(isDisplayed(education)) {
			education.sendKeys("Automation Testing");
			System.out.println("is Enabled");
		}else {
			System.out.println("is Disabled");
		}
		
		if(isDisplayed(age)) {
			age.click();
			System.out.println("is Enabled");
		}else {
			System.out.println("is Disabled");
		}
		
		
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		
	}

	@Test
	public void TC_03_LoginWithInvalidPassword() {
		
	}

	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		
	}

	private boolean isDisplayed(WebElement element) {
		if(element.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
