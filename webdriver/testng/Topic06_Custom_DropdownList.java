package testng;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_Custom_DropdownList {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,15);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropDownList("//span[@id='number-button']","//ul[@id='number-menu']//div","19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='19']")).isDisplayed());
		
		selectItemInDropDownList("//span[@id='number-button']","//ul[@id='number-menu']//div","3");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='3']")).isDisplayed());
		
		selectItemInDropDownList("//span[@id='number-button']","//ul[@id='number-menu']//div","12");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='12']")).isDisplayed());
	}
	
	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropDownList("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Jenny Hess");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(text(),'Jenny Hess')]")).isDisplayed());
		
		selectItemInDropDownList("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Justen Kitsune");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(text(),'Justen Kitsune')]")).isDisplayed());
		
		selectItemInDropDownList("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Stevie Feliciano");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(text(),'Stevie Feliciano')]")).isDisplayed());
	}
	
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropDownList("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		selectItemInDropDownList("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItemInDropDownList("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	}
	
	@Test
	public void TC_04_Default() {
		driver.get("https://demo.nopcommerce.com/register");
		
		selectItemInDropDownList("//select[@name='DateOfBirthDay']","//select[@name='DateOfBirthDay']//option","19");
		Assert.assertTrue(driver.findElement(By.xpath("//option[text()='19']")).isDisplayed());
		
		selectItemInDropDownList("//select[@name='DateOfBirthDay']","//select[@name='DateOfBirthDay']//option","3");
		Assert.assertTrue(driver.findElement(By.xpath("//option[text()='3']")).isDisplayed());
		
		selectItemInDropDownList("//select[@name='DateOfBirthDay']","//select[@name='DateOfBirthDay']//option","30");
		Assert.assertTrue(driver.findElement(By.xpath("//option[text()='30']")).isDisplayed());
	}
	
	public void selectItemInDropDownList(String parentXpath, String childXpath, String expectedItemText) {
		//click on dropdown list
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSec(2);
		
		//Get all items in dropdown list
		List<WebElement> itemList = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//select item matches expected text
		for(WebElement item : itemList){
			if(item.getText().trim().equals(expectedItemText)) {
				//scroll down and select the item
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSec(2);
			}
		}
		
	}

	public void sleepInSec(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
