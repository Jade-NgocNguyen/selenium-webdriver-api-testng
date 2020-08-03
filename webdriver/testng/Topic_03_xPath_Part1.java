package testng;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_xPath_Part1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		String xpathMyAcc = "//*[@class='footer']//*[@title='My Account']";
		WebElement myAccLink;
		myAccLink = driver.findElement(By.xpath(xpathMyAcc));
		myAccLink.click();
		Thread.sleep(3000);
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {		
		WebElement emailTxtBox;
		emailTxtBox = driver.findElement(By.id("email"));
		emailTxtBox.sendKeys("");
		WebElement passTxtBox;
		passTxtBox = driver.findElement(By.id("pass"));
		passTxtBox.sendKeys("");
		
		WebElement loginBtn;
		String logBtnXPath = "//*[@id='send2']";
		loginBtn = driver.findElement(By.xpath(logBtnXPath));
		loginBtn.click();
		
		String errMsg1XPath = "//*[@id='advice-required-entry-email']";
		String errMsg2XPath = "//*[@id='advice-required-entry-pass']";
		String errMsg1 = driver.findElement(By.xpath(errMsg1XPath)).getText();
		String errMsg2 = driver.findElement(By.xpath(errMsg2XPath)).getText();
		String errMsgCompare1 = "This is a required field.";
		String errMsgCompare2 = "This is a required field.";
		assertEquals(errMsg1, errMsgCompare1);
		assertEquals(errMsg2, errMsgCompare2);
		
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {	
		WebElement emailTxtBox;
		emailTxtBox = driver.findElement(By.id("email"));
		emailTxtBox.clear();
		emailTxtBox.sendKeys("123434234@12312.123123");
		WebElement passTxtBox;
		passTxtBox = driver.findElement(By.id("pass"));
		passTxtBox.clear();
		passTxtBox.sendKeys("123456");
		
		WebElement loginBtn;
		String logBtnXPath = "//*[@id='send2']";
		loginBtn = driver.findElement(By.xpath(logBtnXPath));
		loginBtn.click();
		
		String errMsg1XPath = "//*[@id='advice-validate-email-email']";
		String errMsg1 = driver.findElement(By.xpath(errMsg1XPath)).getText();
		String errMsgCompare1 = "Please enter a valid email address. For example johndoe@domain.com.";
		assertEquals(errMsg1, errMsgCompare1);
	}
	
	@Test
	public void TC_03_LoginWithInvalidPassword() {
		WebElement emailTxtBox;
		emailTxtBox = driver.findElement(By.id("email"));
		emailTxtBox.clear();
		emailTxtBox.sendKeys("automation@gmail.com");
		WebElement passTxtBox;
		passTxtBox = driver.findElement(By.id("pass"));
		passTxtBox.clear();
		passTxtBox.sendKeys("123");
		
		WebElement loginBtn;
		String logBtnXPath = "//*[@id='send2']";
		loginBtn = driver.findElement(By.xpath(logBtnXPath));
		loginBtn.click();
		
		String errMsg1XPath = "//*[@id='advice-validate-password-pass']";
		String errMsg1 = driver.findElement(By.xpath(errMsg1XPath)).getText();
		String errMsgCompare1 = "Please enter 6 or more characters without leading or trailing spaces.";
		assertEquals(errMsg1, errMsgCompare1);
	}

	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		WebElement emailTxtBox;
		emailTxtBox = driver.findElement(By.id("email"));
		emailTxtBox.clear();
		emailTxtBox.sendKeys("automation@gmail.com");
		WebElement passTxtBox;
		passTxtBox = driver.findElement(By.id("pass"));
		passTxtBox.clear();
		passTxtBox.sendKeys("123123123");
		
		WebElement loginBtn;
		String logBtnXPath = "//*[@id='send2']";
		loginBtn = driver.findElement(By.xpath(logBtnXPath));
		loginBtn.click();
		
		String errMsg1XPath = "//*[@class='error-msg']//span";
		String errMsg1 = driver.findElement(By.xpath(errMsg1XPath)).getText();
		String errMsgCompare1 = "Invalid login or password.";
		assertEquals(errMsg1, errMsgCompare1);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
