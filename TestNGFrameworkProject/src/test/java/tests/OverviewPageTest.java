package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CompletedPage;
import pages.InformationPage;
import pages.LoginPage;
import pages.NavigationBar;
import pages.OverviewPage;
import pages.ProductListPage;
import pages.ShoppingCartPage;

public class OverviewPageTest extends BaseTest {
	
	LoginPage loginPage;
	ProductListPage productListPage;
	ShoppingCartPage shoppingCartPage;
	InformationPage informationPage;
	OverviewPage overviewPage;
	CompletedPage completedPage;
	NavigationBar navigationBar;

	
	@Test (priority = 1, description = "Test to verify Subtotal on the overview page.", groups= {"Smoke", "Regression"})
	public void TestToVerifySubTotal() throws InterruptedException  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		Assert.assertEquals(overviewPage.getSubtotalString(), data.getProperty("cop_SubTotal"));
	}
	
	@Test (priority = 2, description = "Test to verify Tax on the overview page.", groups= {"Smoke", "Regression"})
	public void TestToVerifyTax() throws InterruptedException  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		Assert.assertEquals(overviewPage.getTaxString(), data.getProperty("cop_Tax"));
	}
	
	@Test (priority = 3, description = "Test to verify Total on the overview page.", groups= {"Smoke", "Regression"})
	 public void TestToVerifyTotal() throws InterruptedException  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		Assert.assertEquals(overviewPage.getTotalString(), data.getProperty("cop_Total"));		
	}
	
	
	@Test (priority = 4, description = "Test to verify that product list page is displayed when cancel button is clicked.", groups= {"Regression"})
	public void testToVerifyProductListPageIsDisplayed() throws InterruptedException  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		productListPage = overviewPage.clickOnCancelButton();
		Assert.assertTrue(productListPage.isPageDisplayed());
		Assert.assertTrue(productListPage.isPageHeaderDisplayed());
		Assert.assertEquals(productListPage.getPageHeaderString(), data.getProperty("plp_pageHeader"));
	}
	
	
	@Test (priority = 5, description = "Test to verify that complete page is displayed when finish button is clicked.", groups= {"Smoke", "Regression"})
	public void testToVerifyCompletePageIsDisplayed() throws InterruptedException  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		completedPage = overviewPage.clickOnFinishButton();
		Assert.assertTrue(completedPage.isPageDisplayed());
		Assert.assertTrue(completedPage.isPageHeaderDisplayed());
		Assert.assertEquals(completedPage.getPageHeaderString(), data.getProperty("ccp_PageHeader"));	
	}
}
