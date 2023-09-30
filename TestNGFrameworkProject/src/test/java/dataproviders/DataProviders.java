package dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "getInValidCredentials")
	public Object[][] getInValidCredentials() {
		Object[][] data = new Object[][] { { "invalid_user", "invalid_pass", "Epic sadface: Username and password do not match any user in this service" } };
		return data;
	}
}
