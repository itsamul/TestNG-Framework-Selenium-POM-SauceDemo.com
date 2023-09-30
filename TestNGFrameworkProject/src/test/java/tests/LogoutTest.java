package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CompletedPage;
import pages.InformationPage;
import pages.LoginPage;
import pages.NavigationBar;
import pages.OverviewPage;
import pages.ProductDetailPage;
import pages.ProductListPage;
import pages.ShoppingCartPage;

public class LogoutTest extends BaseTest {

	ProductListPage productListPage;
	LoginPage loginPage;
	NavigationBar navigationBar;
	ProductDetailPage productDetailPage;
	ShoppingCartPage shoppingCartPage;
	InformationPage informationPage;
	OverviewPage overviewPage;
	CompletedPage completedPage;
	
	@Test(priority = 1, description = "Test to logout from product list page.", groups = { "Smoke", "Regression" })
	public void testLogoutFromProductListPage() {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		navigationBar = new NavigationBar(driver);
		navigationBar.performLogout();
		Assert.assertTrue(loginPage.isPageDisplayed(), "Login page not displayed after logout.");
	}
	
	@Test(priority = 2, description = "Test to logout from information page.", groups = { "Smoke", "Regression" })
	public void testLogoutFromInformationPage() {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		completedPage = overviewPage.clickOnFinishButton();
		navigationBar.performLogout();
		Assert.assertTrue(loginPage.isPageDisplayed(), "Login page not displayed after logout.");
	}

}
