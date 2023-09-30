package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductListPage {

	private WebDriver driver;

	public ProductListPage(WebDriver driver) {
		this.driver = driver;
	}

	private By pageHeader = By.xpath("//span[@class='title']");
	private By inventory_item_name = By.xpath("//div[@class='inventory_item_name']");
	private By product_sort_container = By.xpath("//select[@class='product_sort_container']");
	
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

	public void addProductToCart(String[] productNames) {
		List<String> productsToAdd = Arrays.asList(productNames);
		List<WebElement> allProductsList = driver.findElements(inventory_item_name);
		for (int i = 0; i < allProductsList.size(); i++) {
			String product = allProductsList.get(i).getText();
			int count = 0;
			if (productsToAdd.contains(product)) {
				count++;
				driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + product + "']//following::button[text()='Add to cart']")).click();
				if (count == productNames.length) {
					break;
				}
			}
		}
	}

	public void addProductToCart(String productName) {
		List<WebElement> allProductsList = driver.findElements(inventory_item_name);
		for (int i = 0; i < allProductsList.size(); i++) {
			String product = allProductsList.get(i).getText();
			if (productName.contains(product)) {
				driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + product + "']//following::button[text()='Add to cart']")).click();
				break;
			}
		}
	}

	public boolean sortProducts(String selectedOption) {
		Select select = new Select(driver.findElement(product_sort_container));
		select.selectByVisibleText(selectedOption);
		List<WebElement> listAfterSort = driver.findElements(inventory_item_name);
		List<String> productsList = new ArrayList<String>();
		for (WebElement element : listAfterSort) {
			productsList.add(element.getText());
		}

		if (selectedOption.equalsIgnoreCase("Name (A to Z)")) {
			return isSortedInAscending(productsList);
		} else if (selectedOption.equalsIgnoreCase("Name (Z to A)")) {
			return isSortedInDescending(productsList);
		}
		return false;
	}

	private boolean isSortedInAscending(List<String> arraylist) {
		boolean isSorted = true;
		for (int i = 1; i < arraylist.size(); i++) {
			if (arraylist.get(i - 1).compareTo(arraylist.get(i)) > 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	private boolean isSortedInDescending(List<String> arraylist) {
		boolean isSorted = true;
		for (int i = 1; i < arraylist.size(); i++) {
			if (arraylist.get(i - 1).compareTo(arraylist.get(i)) < 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	public ProductDetailPage ClickOnProductName(String productName) {
		List<WebElement> allProductsList = driver.findElements(inventory_item_name);
		for (int i = 0; i < allProductsList.size(); i++) {
			String product = allProductsList.get(i).getText();
			if (productName.contains(product)) {
				driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + product + "']")).click();
				break;
			}
		}
		return new ProductDetailPage(driver);
	}
	
}