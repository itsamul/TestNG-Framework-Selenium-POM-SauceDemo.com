package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.NavigationBar;
import pages.ProductListPage;
import pages.ShoppingCartPage;

public class ProductListPageTest extends BaseTest {
	
	LoginPage loginPage;
	ProductListPage productListPage;
	ShoppingCartPage shoppingCartPage;
	NavigationBar navigationBar;

	
	@Test (priority = 1, description = "Test to verify products are sorted from Z to A.", groups= {"Regression"})
	public void TestToSortTheProductsFromZToA() throws InterruptedException  {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		Assert.assertTrue(productListPage.sortProducts("Name (Z to A)"));
	}

	@Test (priority = 2, description = "Test to verify products are sorted from A to Z.", groups= {"Regression"})
	public void TestToSortTheProductsFromAToZ() throws InterruptedException  {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		Assert.assertTrue(productListPage.sortProducts("Name (A to Z)"));
	}
	
	@Test (priority = 3, description = "Test to verify the count on the shopping badge is same After adding multiple products to cart.", groups= {"Smoke", "Regression"})
	public void TestTheCountOnCartBadgeAfterAddingMultipleProductsToCart()  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);	
		navigationBar.getCartBadgeCount();
		Assert.assertEquals(navigationBar.getCartBadgeCount(), Integer.toString(productNames.length));	

	}
	
	@Test (priority = 4, description = "Test to verify the count on the shopping badge is same After adding single products to cart.", groups= {"Smoke", "Regression"})
	public void TestTheCountOnCartBadgeAfterAddingSingleProductToCart()  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String productName = "Sauce Labs Bolt T-Shirt";
		productListPage.addProductToCart(productName);
		navigationBar.getCartBadgeCount();
		Assert.assertEquals(navigationBar.getCartBadgeCount(), "1");	
	}
	

	@Test (priority = 5, description = "Test to verify that shopping cart page is displayed when cart icon is clicked.", groups= {"Regression"})
	public void TestToVerifyShoppingCartPageIsDisplayed()  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String productName = "Sauce Labs Bolt T-Shirt";
		productListPage.addProductToCart(productName);
		shoppingCartPage = navigationBar.clickOnCartButton();
		Assert.assertTrue(shoppingCartPage.isPageDisplayed());	
		Assert.assertTrue(shoppingCartPage.isPageHeaderDisplayed());
		Assert.assertEquals(shoppingCartPage.getPageHeaderString(), data.getProperty("scp_PageHeader"));
		
	}
}
