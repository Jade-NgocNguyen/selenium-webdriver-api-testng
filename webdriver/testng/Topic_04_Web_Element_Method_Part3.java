package testng;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//*[@class='footer']//*[@title='My Account']")).click();

		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("");

		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("");

		driver.findElement(By.xpath("//*[@id='send2']")).click();

		String errMsg1XPath = "//*[@id='advice-required-entry-email']";
		String errMsg2XPath = "//*[@id='advice-required-entry-pass']";
		String errMsg1 = driver.findElement(By.xpath(errMsg1XPath)).getText();
		String errMsg2 = driver.findElement(By.xpath(errMsg2XPath)).getText();

		assertEquals(errMsg1, "This is a required field.");
		assertEquals(errMsg2, "This is a required field.");

	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//*[@class='footer']//*[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");

		driver.findElement(By.id("pass")).sendKeys("123456");

		driver.findElement(By.xpath("//*[@id='send2']")).click();

		String errMsg1XPath = "//*[@id='advice-validate-email-email']";
		String errMsg1 = driver.findElement(By.xpath(errMsg1XPath)).getText();

		assertEquals(errMsg1, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithInvalidPassword() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//*[@class='footer']//*[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("123");

		driver.findElement(By.xpath("//*[@id='send2']")).click();

		String errMsg1 = driver.findElement(By.xpath("//*[@id='advice-validate-password-pass']")).getText();

		assertEquals(errMsg1, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//*[@class='footer']//*[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("123123123");

		driver.findElement(By.xpath("//*[@id='send2']")).click();

		String errMsg1 = driver.findElement(By.xpath("//*[@class='error-msg']//span")).getText();

		assertEquals(errMsg1, "Invalid login or password.");
	}

	@Test
	public void TC_05_LoginWithValidEmailAndPassword() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//*[@class='footer']//*[@title='My Account']")).click();

		String email = "automation_13@gmail.com";
		String fullName = "Automation Testing";

		driver.findElement(By.id("email")).sendKeys(email);

		driver.findElement(By.id("pass")).sendKeys("123123");

		driver.findElement(By.xpath("//*[@id='send2']")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='dashboard']//h1[text()='My Dashboard']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(),
				"Hello, " + fullName + "!");

		String contactInfoText = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
		Assert.assertTrue(contactInfoText.contains(fullName));
		Assert.assertTrue(contactInfoText.contains(email));

		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		driver.findElement(By.xpath("//div[@class='links']//li[last()]/a")).click();

	}

	@Test
	public void TC_06_CreateNewAccount() {
		Random rand = new Random();
		int emailTail = rand.nextInt(999999);
		String firstName = "Auto";
		String lastName = "FC";
		String email = "automation_" + String.valueOf(emailTail) + "@gmail.com";

		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//li[@class='first']/a[text()='My Account']")).click();

		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pass123");

		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("pass123");

		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@class='button']")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering')]")).isDisplayed());

		String accInfo = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();

		Assert.assertTrue(accInfo.contains(firstName));

		Assert.assertTrue(accInfo.contains(lastName));

		Assert.assertTrue(accInfo.contains(email));

		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		driver.findElement(By.xpath("//div[@class='links']//li[last()]/a")).click();

//		try {
//			Thread.sleep(7000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		Assert.assertTrue(driver.getTitle().contains("Home page"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
