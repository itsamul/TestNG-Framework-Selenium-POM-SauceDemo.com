package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductListPage;

public class ProductDetailPageTest extends BaseTest {

	LoginPage loginPage;
	ProductListPage productListPage;
	ProductDetailPage productDetailPage;

	@Test(priority = 1, description = "Test to verify that the product name is displayed on the item page.", groups= {"Smoke", "Regression"})
	public void testToVerifyTheProductName() {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		productDetailPage = productListPage.ClickOnProductName("Sauce Labs Bolt T-Shirt");
		Assert.assertEquals(productDetailPage.getInventoryNameString(),"Sauce Labs Bolt T-Shirt");
	}

	@Test(priority = 2, description = "Test to verify that the product description is displayed on the item page.", groups= {"Regression"})
	public void testToVerifyTheProductDescription() {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		productDetailPage = productListPage.ClickOnProductName("Sauce Labs Bolt T-Shirt");
		Assert.assertEquals(productDetailPage.getInventoryDescriptionString(),"Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.");
	}

	@Test(priority = 3, description = "Test to verify that the product price is displayed on the item page.", groups= {"Regression"})
	public void testToVerifyTheProductPrice() {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		productDetailPage = productListPage.ClickOnProductName("Sauce Labs Bolt T-Shirt");
		Assert.assertEquals(productDetailPage.getInventoryPriceString(),"15.99");
	}

	@Test(priority = 4, description = "Test to verify that the inventory page is displayed when back to product page is clicked.", groups= {"Regression"})
	public void testToVerifyInventoryPageIsDisplayed() {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		productDetailPage = productListPage.ClickOnProductName("Sauce Labs Bolt T-Shirt");
		productListPage = productDetailPage.clickOnBackToProductsButton();
		Assert.assertTrue(productListPage.isPageDisplayed());
		
	}
}
