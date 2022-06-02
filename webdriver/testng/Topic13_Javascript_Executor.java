package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic13_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_JE_Executor() {
		String URL;
		String verifiedText;
		String displayedText;
		String xPath;
		// Step 1: navigate to site http://live.techpanda.org/ by JE
		URL = "http://live.techpanda.org/";
		this.navigateToURLByJs(this.driver, URL);
		System.out.println("End of step 1");
		
		// Step 2: get and verify that domain = live.techpanda.org by JE
		verifiedText = "live.techpanda.org";
		String actualDomain = this.getDomainByJs(this.driver);
		this.verifyText(actualDomain, verifiedText);
		System.out.println("End of step 2");
		
		// Step 3: get and verify that URL = http://live.techpanda.org/ by JE
		String actualURL = this.getURLByJs(this.driver);
		this.verifyText(actualURL, URL);
		System.out.println("End of step 3");
		
		// Step 4: open MOBILE page by JE
		xPath = "//div//a[text()='Mobile']";
		WebElement mobileMenu = this.findElementBy(this.driver, By.xpath(xPath));
		this.clickOnElementByJs(this.driver, mobileMenu);
		System.out.println("End of step 4");
		
		// Step 5: add SAMSUNG GALAXY to cart by JE
		xPath = "//li[contains(@class,'item last')][2]//button";
		WebElement addToCartBtn = this.findElementBy(this.driver, By.xpath(xPath));
		this.clickOnElementByJs(this.driver, addToCartBtn);
		this.sleepInSec(5);
		System.out.println("End of step 5");

		
		 // Step 6: verify that msg is displayed as "Samsung Galaxy was added to your shopping cart." by JE 
		 verifiedText = "Samsung Galaxy was added to your shopping cart."; 
		 displayedText = this.getInnterTextByJs(this.driver); 
		 //System.out.println("actual text:" + displayedText);
		 this.verifyText(displayedText, verifiedText);
		 System.out.println("End of step 6");

		// Step 7: open customer service page and verify the title of page by JE
		// Scroll down
		this.scrollToPixelByJs(this.driver, 300);
		
		// Click to Customer Service menu
		xPath = "//div[@class = 'footer']//a[text() = 'Customer Service']";
		WebElement customerServcieMenu = this.findElementBy(this.driver, By.xpath(xPath));
		this.clickOnElementByJs(this.driver, customerServcieMenu);
		
		// Verify the page title
		verifiedText = "CUSTOMER SERVICE";
		displayedText = this.getInnterTextByJs(this.driver);
		//System.out.println("Actual text:" + displayedText);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 7");
		
		// Step 8: scroll to element Newsletter textbox by JE
		// Scroll to element
		xPath = "//div[@class='input-box']//input[@id='newsletter']";
		WebElement textbox = this.findElementBy(this.driver, By.xpath(xPath));
		this.scrollToElementByJs(this.driver, textbox);
		System.out.println("End of step 8");
		
		// Step 9: input email to Newsletter textbox by JE
		String email = this.generateRandomMail();
		this.sendKeyToElementByJs(this.driver, textbox, email);
		System.out.println("End of step 9");

		// Step 10: click to Subscribe button by JE
		xPath = "//div[@class='actions']//span[text()='Subscribe']";
		WebElement subscribeBtn = this.findElementBy(this.driver, By.xpath(xPath));
		this.clickOnElementByJs(this.driver, subscribeBtn);
		System.out.println("End of step 10");
		
		// Step 11: verify text displayed as  by JE
		verifiedText = "Thank you for your subscription.";
		displayedText = this.getInnterTextByJs(this.driver);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 11");
		
		// Step 12: navigate to site http://demo.guru99.com/v4/ and verify the domain by JE
		URL = "http://demo.guru99.com/v4/";
		this.navigateToURLByJs(this.driver, URL);
				
		// get and verify that domain = http://demo.guru99.com/v4/ by JE
		verifiedText = "demo.guru99.com";
		actualDomain = this.getDomainByJs(this.driver);
		this.verifyText(actualDomain, verifiedText);
		System.out.println("End of step 12");
	}
	
	@Test
	public void TC_02_Verify_HMTL5_Validation_Msg() {
		String URL;
		String verifiedText;
		String displayedText;
		String xPath;
		// Step 1: navigate to site https://automationfc.github.io/html5/index.html by JE
		URL = "https://automationfc.github.io/html5/index.html";
		this.navigateToURLByJs(this.driver, URL);
		System.out.println("End of step 1");
		
		// Step 2: click Submit and verify validation message
		// Click on Submit
		xPath = "//div[@class='container']//input[@class='btn' and @value='SUBMIT']";
		WebElement submitBtn = this.findElementBy(this.driver, By.xpath(xPath));
		submitBtn.click();
		
		// Verify the validation msg on Name text field
		verifiedText = "Please fill out this field.";
		xPath = "//div[@class='container']//input[@type='name']";
		WebElement nameTxtBox = this.findElementBy(this.driver, By.xpath(xPath));
		displayedText = this.getValidationMsgByJs(this.driver, nameTxtBox);
		System.out.println("Validation msg:" + displayedText);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 2");
		
		// Step 3: Fill in Name text field then click on Submit and verify the validation message
		// Fill in the Name text field
		this.sendKeyToElementByJs(this.driver, nameTxtBox, "Ngoc Nguyen");
		
		// Click on Submit
		this.clickOnElementByJs(this.driver, submitBtn);
		
		// Verify validation msg on Password text field
		xPath = "//div[@class='container']//input[@type='password']";
		WebElement passwordTxtBox = this.findElementBy(this.driver, By.xpath(xPath));
		displayedText = this.getValidationMsgByJs(this.driver, passwordTxtBox);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 3");
		
		// Step 4: Fill in Password text field then click on Submit and verify the validation message on Email
		// Fill in the Password text field
		this.sendKeyToElementByJs(this.driver, passwordTxtBox, "12345");
				
		// Click on Submit
		this.clickOnElementByJs(this.driver, submitBtn);
				
		// Verify validation msg on Email text field
		xPath = "//div[@class='container']//input[@type='email']";
		WebElement emailTxtBox = this.findElementBy(this.driver, By.xpath(xPath));
		displayedText = this.getValidationMsgByJs(this.driver, emailTxtBox);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 4");
		
		// Step 5: Fill in Email text field with illegal data then click on Submit and verify the validation message on Email
		// Fill in the Email text field
		String email = "abc";
		this.sleepInSec(2);
		this.sendKeyToElementByJs(this.driver, emailTxtBox, email);
						
		// Click on Submit
		this.sleepInSec(2);
		this.clickOnElementByJs(this.driver, submitBtn);
		displayedText = this.getValidationMsgByJs(this.driver, emailTxtBox);
		System.out.println("Acutual text:" + displayedText);
						
		// Verify validation msg on Email text field
		verifiedText = "Please include the '@' in the email address.'" + email + "' is missing an '@'.";
		System.out.println("Verified text:" + verifiedText);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 5");
		
		// Step 6: Fill in Email text field with illegal data then click on Submit and verify the validation message on Email
		// Fill in the Email text field
		email = "123@456";
		this.sendKeyToElementByJs(this.driver, emailTxtBox, "");
		this.sendKeyToElementByJs(this.driver, emailTxtBox, email);
								
		// Click on Submit
		this.clickOnElementByJs(this.driver, submitBtn);
								
		// Verify validation msg on Email text field
		verifiedText = "Please match the requested format.";
		displayedText = this.getValidationMsgByJs(this.driver, emailTxtBox);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 6");
		
		// Step 7: Fill in Email text field with correct data then click on Submit and verify the validation message on Email
		// Fill in the Email text field
		email = "abc@gmail.com";
		this.sendKeyToElementByJs(this.driver, emailTxtBox, email);
										
		// Click on Submit
		this.clickOnElementByJs(this.driver, submitBtn);
										
		// Verify validation msg on Address field
		xPath = "//div[@class='container']//b[text()='✱ ADDRESS ']";
		WebElement emailDropdown = this.findElementBy(this.driver, By.xpath(xPath));
		verifiedText = "Please an item in the list.";
		displayedText = this.getValidationMsgByJs(this.driver, emailDropdown);
		this.verifyText(displayedText, verifiedText);
		System.out.println("End of step 7");	
	}
	
	public WebElement findElementBy(WebDriver driver, By by) {
		return driver.findElement(by);
	}
	
	public void navigateToURLByJs(WebDriver driver, String URL) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.location = '" + URL + "'");
	}

	public String getDomainByJs(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		return (String)jsExecutor.executeScript("return document.domain");
	}
	
	public String getURLByJs(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		return (String)jsExecutor.executeScript("return document.URL");
	}
	
	public void clickOnElementByJs(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();",element); 
	}
	
	public String getInnterTextByJs(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		return jsExecutor.executeScript("return document.documentElement.innerText;").toString(); 
	}
	
	public void scrollToPixelByJs(WebDriver driver, int pixel) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0," + pixel + ");"); 
	}
	
	public void scrollToElementByJs(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element); 
	}
	
	public void sendKeyToElementByJs(WebDriver driver, WebElement element, String value) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value','" + value + "')", element); 
	}
	
	public String getValidationMsgByJs(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor)driver;
		return (String)jsExecutor.executeScript("return arguments[0].validationMessage;", element); 
	}
	
	public void verifyText(String verifiedText, String expectedText) {
		Assert.assertTrue(verifiedText.contains(expectedText));
	}
	
	public String generateRandomMail() {
		Random rand = new Random();
		int emailTail = rand.nextInt(999999);
		String email = "automation_" + String.valueOf(emailTail) + "@gmail.com";
		return email;
	}
	
	public void sleepInSec(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean waitForJQueryAndJsLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		jsExecutor = (JavascriptExecutor)driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.isActive") == 0);
				}catch (Exception e){
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return jQuery.readyState").toString().equals("Complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
