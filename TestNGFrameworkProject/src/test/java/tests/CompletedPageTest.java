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

public class CompletedPageTest extends BaseTest {
	
	LoginPage loginPage;
	ProductListPage productListPage;
	ShoppingCartPage shoppingCartPage;
	InformationPage informationPage;
	OverviewPage overviewPage;
	CompletedPage completedPage;
	NavigationBar navigationBar;
	
	@Test (priority = 1, description = "Test to verify completed order message on the overview page.", groups= {"Smoke", "Regression"})
	public void TestToVerifyOrderCompletedMessage() {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		completedPage = overviewPage.clickOnFinishButton();
		Assert.assertTrue(completedPage.isMessageDisplayed());
		Assert.assertEquals(completedPage.getMessageString(),data.getProperty("ccp_message"));
	}

	@Test (priority = 2, description = "Test to verify that product list page is displayed when back home button is clicked.", groups= {"Regression"})
	public void testToVerifyProductListPageIsDisplayed() {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		completedPage = overviewPage.clickOnFinishButton();
		completedPage.clickOnBackHomeButton();
		Assert.assertTrue(productListPage.isPageDisplayed());
		Assert.assertTrue(productListPage.isPageHeaderDisplayed());
		Assert.assertEquals(productListPage.getPageHeaderString(), data.getProperty("plp_pageHeader"));
	}
	
	
	@Test (priority = 3, description = "Test to verify that complete page is displayed when finish button is clicked.", groups= {"Smoke", "Regression"})
	public void testToVerifyCompletePageIsDisplayed() {
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
