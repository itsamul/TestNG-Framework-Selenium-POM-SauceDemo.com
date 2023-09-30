package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {
	WebDriver driver;

	public NavigationBar(WebDriver driver) {
		this.driver = driver;
	}

	private By menu_button = By.id("react-burger-menu-btn");
	private By logout_sidebar_link = By.id("logout_sidebar_link");
	private By shopping_cart_link = By.xpath("//a[@class='shopping_cart_link']");
	private By shopping_cart_badge = By.xpath("//span[@class='shopping_cart_badge']");
	
	public boolean isMenuButtonDisplayed() {
		try {
			return driver.findElement(menu_button).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public LoginPage performLogout() {
		try {
			driver.findElement(menu_button).click();
			driver.findElement(logout_sidebar_link).click();
			return new LoginPage(driver);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getCartBadgeCount() {
		try {
			return driver.findElement(shopping_cart_badge).getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public ShoppingCartPage clickOnCartButton() {
		driver.findElement(shopping_cart_link).click();
		return new ShoppingCartPage(driver);
	}
	
}
