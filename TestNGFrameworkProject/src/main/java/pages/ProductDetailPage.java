package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage {

	private WebDriver driver;

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
	}

	private By inventory_details_back_button = By.id("back-to-products");
	private By inventory_details_name = By.xpath("//div[@class='inventory_details_name large_size']");
	private By inventory_details_desc = By.xpath("//div[@class='inventory_details_desc large_size']");
	private By inventory_details_price = By.xpath("//div[@class='inventory_details_price']");

	public String getInventoryNameString() {
		try {
			return driver.findElement(inventory_details_name).getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}	
	}
	
	public String getInventoryDescriptionString() {
		try {
			return driver.findElement(inventory_details_desc).getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}	
	}
	
	public String getInventoryPriceString() {
		try {
			return driver.findElement(inventory_details_price).getText().replace("$", "");
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}	
	}
	
	public ProductListPage clickOnBackToProductsButton() {
		driver.findElement(inventory_details_back_button).click();
		return new ProductListPage(driver);
	}
}
