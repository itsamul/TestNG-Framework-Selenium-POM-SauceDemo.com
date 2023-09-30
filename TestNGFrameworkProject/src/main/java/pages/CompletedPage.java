package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletedPage {

	private WebDriver driver;

	public CompletedPage(WebDriver driver) {
		this.driver = driver;
	}

	private By pageHeader = By.xpath("//span[@class='title']");
	private By backHome_button = By.id("back-to-products");
	private By message = By.xpath("//h2");
	
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
	
	public boolean isMessageDisplayed() {
		try {
			return driver.findElement(message).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getMessageString () {
		try {
			return driver.findElement(message).getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public ProductListPage clickOnBackHomeButton() {
		driver.findElement(backHome_button).click();
		return new ProductListPage(driver);
	}


	
}
