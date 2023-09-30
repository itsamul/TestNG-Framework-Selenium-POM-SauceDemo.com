package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {

	private WebDriver driver;

	public InformationPage(WebDriver driver) {
		this.driver = driver;
	}

	private By pageHeader = By.xpath("//span[@class='title']");
	private By firstName_input = By.id("first-name");
	private By lastName_input = By.id("last-name");
	private By postalCode_input = By.id("postal-code");
	private By continue_button = By.id("continue");
	private By cancel_button = By.id("cancel");
	private By error_message = By.xpath("//h3");

	public boolean isPageDisplayed() {
		try {
			return driver.findElement(pageHeader).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isPageHeaderDisplayed() {
		try {
			return driver.findElement(pageHeader).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getPageHeaderString() {
		try {
			return driver.findElement(pageHeader).getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public boolean isErrorMessageDisplayed() {
		try {
			return driver.findElement(error_message).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getErrorMessageString() {
		try {
			return driver.findElement(error_message).getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public ShoppingCartPage clickOnCancelButton() {
		driver.findElement(cancel_button).click();
		return new ShoppingCartPage(driver);
	}
	
	public OverviewPage clickOnContinueButton() {
		driver.findElement(continue_button).click();
		return new OverviewPage(driver);
	}
	
	public OverviewPage enterInformation(String firstName, String lastName, String postalCode) {
		driver.findElement(firstName_input).clear();
		driver.findElement(firstName_input).sendKeys(firstName);
		driver.findElement(lastName_input).clear();
		driver.findElement(lastName_input).sendKeys(lastName);
		driver.findElement(postalCode_input).clear();
		driver.findElement(postalCode_input).sendKeys(postalCode);
		driver.findElement(continue_button).click();
		return new OverviewPage(driver);
	}
}
