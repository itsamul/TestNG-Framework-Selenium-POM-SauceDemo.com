package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	private By username_field = By.id("user-name");
	private By password_field = By.id("password");
	private By login_button = By.id("login-button");
	private By error_message = By.xpath("//h3");

	public boolean isPageDisplayed() {
		try {
			return driver.findElement(login_button).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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

	public ProductListPage performLogin(String username, String password) {
		driver.findElement(username_field).clear();
		driver.findElement(username_field).sendKeys(username);
		driver.findElement(password_field).clear();
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(login_button).click();
		return new ProductListPage(driver);
	}

}
