package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Default_DropdownList {
	WebDriver driver;
	
	//Registration info
	String firstName;
	String lastName;
	String dob;
	String mob;
	String yob;
	String email;
	String password;
	
	//locator & select
	By genderRadio;
	By firstNameTxtbox;
	By lastNameTxtbox;
	By emailTxtbox;
	By passTxtbox;
	By confpassTxtbox;
	Select dateSelect;
	Select monSelect;
	Select yearSelect;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com");
		
		//Set locator
		genderRadio = By.id("gender-male");
		firstNameTxtbox = By.id("FirstName");
		lastNameTxtbox = By.id("LastName");
		emailTxtbox = By.id("Email");
		passTxtbox = By.id("Password");
		confpassTxtbox = By.id("ConfirmPassword");
		
		//Init data
		firstName = "Adele";
		lastName = "Aloha";
		dob = "1";
		mob ="May";
		yob = "1980";
		email = getRandomMail();
		password = "123456";
				
	}

	@Test
	public void TC_01_Default_DropdownList() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		//Fill registration info
		driver.findElement(genderRadio).click();;
		
		driver.findElement(firstNameTxtbox).sendKeys(firstName);
		
		driver.findElement(lastNameTxtbox).sendKeys(lastName);
		
		dateSelect = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		dateSelect.selectByVisibleText(dob);
		
		Assert.assertEquals(dateSelect.getOptions().size(), 32);
		
		monSelect = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		
		monSelect.selectByVisibleText(mob);
		
		Assert.assertEquals(monSelect.getOptions().size(), 13);
		
		yearSelect = new Select(driver.findElement(By.name("DateOfBirthYear")));
		
		yearSelect.selectByVisibleText(yob);
		
		Assert.assertEquals(yearSelect.getOptions().size(), 112);
		
		driver.findElement(emailTxtbox).sendKeys(email);
		
		driver.findElement(passTxtbox).sendKeys(password);
		
		driver.findElement(confpassTxtbox).sendKeys(password);
		
		
		//Click on Register button and verify register successfully
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		
		//Click on My account button
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		
		
		//Verify registered info
		Assert.assertTrue(driver.findElement(genderRadio).isSelected());
		
		Assert.assertEquals(driver.findElement(firstNameTxtbox).getAttribute("value"), firstName);
		
		Assert.assertEquals(driver.findElement(lastNameTxtbox).getAttribute("value"), lastName);
		
		dateSelect = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		Assert.assertEquals(dateSelect.getFirstSelectedOption().getText(), dob);
		
		monSelect = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		
		Assert.assertEquals(monSelect.getFirstSelectedOption().getText(), mob);
		
		yearSelect = new Select(driver.findElement(By.name("DateOfBirthYear")));
		
		Assert.assertEquals(yearSelect.getFirstSelectedOption().getText(), yob);
		
		Assert.assertEquals(driver.findElement(emailTxtbox).getAttribute("value"), email);
		
	}

	public String getRandomMail() {
		Random random = new Random();
		return "adele_" + random.nextInt(999999) + "@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
