package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InformationPage;
import pages.LoginPage;
import pages.NavigationBar;
import pages.OverviewPage;
import pages.ProductListPage;
import pages.ShoppingCartPage;

public class InformationPageTest extends BaseTest {
	
	LoginPage loginPage;
	ProductListPage productListPage;
	ShoppingCartPage shoppingCartPage;
	InformationPage informationPage;
	OverviewPage overviewPage;
	NavigationBar navigationBar;
	
	@Test (priority = 1, description = "Test to check error message is displayed when not information is entered and continue button is clicked.", groups= {"Regression"})
	public void TestToVerifyErrorMessageOnInformationPage() {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		informationPage.clickOnContinueButton();
		Assert.assertTrue(informationPage.isErrorMessageDisplayed(), "Error message not displayed for blank input.");
		Assert.assertEquals(informationPage.getErrorMessageString(), data.getProperty("cip_InputMissingErrorMessage"));		
	}
	
	@Test (priority = 2, description = "Test to verify that checkout overview page is displayed when continue button is clicked.", groups= {"Smoke", "Regression"})
	public void testToCheckOverivewPageIsDisplayed()  {
		loginPage = new LoginPage(driver);
		navigationBar = new NavigationBar(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();
		overviewPage = informationPage.enterInformation(data.getProperty("cip_FirstName"), data.getProperty("cip_LastName"), data.getProperty("cip_PostalCode"));
		Assert.assertTrue(overviewPage.isPageDisplayed());
		Assert.assertTrue(overviewPage.isPageHeaderDisplayed());
		Assert.assertEquals(overviewPage.getPageHeaderString(), data.getProperty("cop_PageHeader"));
	}
	
	@Test (priority = 3, description = "Test to verify Shopping cart page is displayed when cancel button is clicked on Information page.", groups= {"Regression"})
	public void testToVerifyCheckoutInformationPageIsDisplayed() {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt"};
		productListPage.addProductToCart(productNames);
		navigationBar = new NavigationBar(driver);
		shoppingCartPage = navigationBar.clickOnCartButton();
		informationPage = shoppingCartPage.clickOnCheckoutButton();		
		shoppingCartPage = informationPage.clickOnCancelButton();
		Assert.assertTrue(shoppingCartPage.isPageDisplayed());
		Assert.assertTrue(shoppingCartPage.isPageHeaderDisplayed());
		Assert.assertEquals(shoppingCartPage.getPageHeaderString(), data.getProperty("scp_PageHeader"));		
	}
}
