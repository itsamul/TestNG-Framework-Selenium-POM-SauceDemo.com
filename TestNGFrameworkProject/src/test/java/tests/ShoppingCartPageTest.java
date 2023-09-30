package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InformationPage;
import pages.LoginPage;
import pages.NavigationBar;
import pages.ProductListPage;
import pages.ShoppingCartPage;

public class ShoppingCartPageTest extends BaseTest {

	LoginPage loginPage;
	ProductListPage productListPage;
	ShoppingCartPage shoppingCartPage;
	NavigationBar navigationBar;

	@Test(priority = 1, description = "Test to verify that products are added to cart are present on the cart.", groups = {
			"Smoke", "Regression" })
	public void TestToVerifyProductsAddedToCartArePresentInCart() {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt" };
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		Assert.assertTrue(shoppingCartPage.productPresentInCart(productNames));
	}

	@Test(priority = 2, description = "Test to verify that products list page is displayed when continue shopping button is clicked on the cart.", groups = {
			"Regression" })
	public void testToVerifyProductListPageIsDisplayed() {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt" };
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		productListPage = shoppingCartPage.gotoProductListPage();
		Assert.assertTrue(productListPage.isPageDisplayed());
	}

	@Test(priority = 3, description = "Test to verify that checkout information page is displayed when checkout button is clicked on the cart.", groups = {
			"Regression" })
	public void testToVerifyCheckoutInformationPageIsDisplayed() {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt" };
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		InformationPage informationPage = shoppingCartPage.clickOnCheckoutButton();
		Assert.assertTrue(informationPage.isPageDisplayed());
	}
}
