package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataproviders.DataProviders;
import pages.LoginPage;
import pages.ProductListPage;

public class LoginPageTest extends BaseTest{
	
	ProductListPage productListPage;
	LoginPage loginPage;
	
	@Test(priority = 1, description = "Test to verify product list page is displayed after login with valid credentials.", groups= {"Smoke", "Regression"})
	public void testLoginWithValidCredentials() {
		loginPage = new LoginPage(driver);
		productListPage = loginPage.performLogin(data.getProperty("validUserName"), data.getProperty("validPassword"));
		Assert.assertTrue(productListPage.isPageDisplayed(), "Inventory page not displayed after successful login.");
	}
	
	@Test(priority = 2, description = "Test to verify error message is displayed after login with invalid credentials.", groups= {"Smoke", "Regression"}, dataProvider = "getInValidCredentials", dataProviderClass = DataProviders.class)
	public void testLoginWithInvalidCredentials(String usernameValue, String passwordValue, String errorMessage) {
		loginPage = new LoginPage(driver);
		loginPage.performLogin(usernameValue, passwordValue);
		Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid credentials.");
		Assert.assertEquals(loginPage.getErrorMessageString(), errorMessage);
	}
	
	@Test(priority = 3, description = "Test to verify error message is displayed after login with locked credentials.", groups= {"Regression"})
	public void testLoginWithInvalidCredentials() {
		loginPage = new LoginPage(driver);
		loginPage.performLogin(data.getProperty("lockedUserName"), data.getProperty("validPassword"));
		Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for locked credentials.");
		Assert.assertEquals(loginPage.getErrorMessageString(), data.getProperty("lockedUserErrorMessage"));
	}
}
