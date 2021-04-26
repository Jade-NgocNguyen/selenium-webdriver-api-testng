package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_TextBox_TextArea {
	WebDriver driver;

	String randomEmail;
	String loginUrl;
	String userID;
	String password;
	String gender;
	String cusName;
	String dob;
	String address;
	String city;
	String state;
	String pinNo;
	String phoneNo;
	String customerID;

	
	//Edit info variables
	String editAddress;
	String editCity;
	String editState;
	String editPinNo;
	String editPhoneNo;
	String editEmail;

	By cusNameTxtBox = By.name("name");
	By dobTxtBox = By.name("dob");
	By addressTxtBox = By.name("addr");
	By cityTxtBox = By.name("city");
	By stateTxtBox = By.name("state");
	By pinnoTxtBox = By.name("pinno");
	By phoneTxtBox = By.name("telephoneno");
	By emailTxtBox = By.name("emailid");
	By passwordTxtBox = By.name("password");
	By submitBtn = By.name("sub");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");

		//Init data
		randomEmail = getRandomEmail();
		cusName = "Hugh Grant";
		gender = "male";
		dob = "1960-11-02";
		address = "16 BT Sanrosa";
		city = "Los Angeles";
		state = "California";
		pinNo = "123456";
		phoneNo = "0038249674";
		
		editAddress = "209 BT Sanrosa";
		editCity = "New York";
		editState = "Washington DC";
		editPinNo = "333666";
		editPhoneNo = "1122334455";
		editEmail = getRandomEmail();
	
	}

	@Test
	public void TC_01_Register() {
		loginUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(randomEmail);

		driver.findElement(By.name("btnLogin")).click();

		// mngr26593
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();

		// 1!
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);

		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_NewCustomer() {
		//Add new customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(cusNameTxtBox).sendKeys(cusName);
		
		driver.findElement(By.xpath("//input[@value='m']")).click();
		
		driver.findElement(dobTxtBox).sendKeys(dob);
		
		driver.findElement(addressTxtBox).sendKeys(address);
		
		driver.findElement(cityTxtBox).sendKeys(city);
		
		driver.findElement(stateTxtBox).sendKeys(state);
		
		driver.findElement(pinnoTxtBox).sendKeys(pinNo);
		
		driver.findElement(phoneTxtBox).sendKeys(phoneNo);
		
		driver.findElement(emailTxtBox).sendKeys(randomEmail);
		
		driver.findElement(passwordTxtBox).sendKeys(password);
		
		driver.findElement(submitBtn).click();
		
		//Verify customer added
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), cusName);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNo);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNo);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), randomEmail);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
	}

	@Test
	public void TC_04_EditCustomer() {
		//input customerID to edit
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		driver.findElement(By.name("cusid")).sendKeys(customerID);

		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify info before edition
		Assert.assertEquals(driver.findElement(cusNameTxtBox).getAttribute("value"), cusName);
		
		Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), gender);
		
		Assert.assertEquals(driver.findElement(dobTxtBox).getAttribute("value"), dob);
		
		Assert.assertEquals(driver.findElement(addressTxtBox).getText(), address);
		
		Assert.assertEquals(driver.findElement(cityTxtBox).getAttribute("value"), city);
		
		Assert.assertEquals(driver.findElement(stateTxtBox).getAttribute("value"), state);
		
		Assert.assertEquals(driver.findElement(pinnoTxtBox).getAttribute("value"), pinNo);
		
		Assert.assertEquals(driver.findElement(phoneTxtBox).getAttribute("value"), phoneNo);
		
		Assert.assertEquals(driver.findElement(emailTxtBox).getAttribute("value"), randomEmail);
		
		//Edit info
		driver.findElement(addressTxtBox).clear();
		driver.findElement(addressTxtBox).sendKeys(editAddress);
		
		driver.findElement(cityTxtBox).clear();
		driver.findElement(cityTxtBox).sendKeys(editCity);
		
		driver.findElement(stateTxtBox).clear();
		driver.findElement(stateTxtBox).sendKeys(editState);
		
		driver.findElement(pinnoTxtBox).clear();
		driver.findElement(pinnoTxtBox).sendKeys(editPinNo);
		
		driver.findElement(phoneTxtBox).clear();
		driver.findElement(phoneTxtBox).sendKeys(editPhoneNo);
		
		driver.findElement(emailTxtBox).clear();
		driver.findElement(emailTxtBox).sendKeys(editEmail);
		
		driver.findElement(submitBtn).click();
		
		//Verify info after edition
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer details updated Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), cusName);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPinNo);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhoneNo);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	}

	String getRandomEmail() {
		Random random = new Random();

		return "testUser_" + random.nextInt(999999) + "@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
