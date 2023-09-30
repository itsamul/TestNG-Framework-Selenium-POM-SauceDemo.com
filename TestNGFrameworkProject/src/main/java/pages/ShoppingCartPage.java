package pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage {

	private WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
	}

	private By pageHeader = By.xpath("//span[@class='title']");
	private By inventory_item_name = By.xpath("//div[@class='cart_item']//div[@class='inventory_item_name']");
	private By continue_shopping_button = By.id("continue-shopping");
	private By checkout_button = By.id("checkout");

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

	public boolean productPresentInCart(String[] productNames) {
		List<WebElement> productsInCart = driver.findElements(inventory_item_name);
		List<String> productNamesAsInput = Arrays.asList(productNames);
		if (productNamesAsInput.size() == productsInCart.size()) {
			for (WebElement element : productsInCart) {
				return productNamesAsInput.contains(element.getText()) ? true : false;
			}
		}
		return false;
	}

	public ProductListPage gotoProductListPage() {
		driver.findElement(continue_shopping_button).click();
		return new ProductListPage(driver);
	}

	public InformationPage clickOnCheckoutButton() {
		driver.findElement(checkout_button).click();
		return new InformationPage(driver);
	}
}
