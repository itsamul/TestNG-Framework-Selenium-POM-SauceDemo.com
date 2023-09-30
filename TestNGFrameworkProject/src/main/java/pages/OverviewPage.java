package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {

	private WebDriver driver;

	public OverviewPage(WebDriver driver) {
		this.driver = driver;
	}

	private By pageHeader = By.xpath("//span[@class='title']");
	private By finish_button = By.id("finish");
	private By cancel_button = By.id("cancel");
	private By summary_subtotal_label = By.xpath("//div[@class='summary_subtotal_label']");
	private By summary_tax_label = By.xpath("//div[@class='summary_tax_label']");
	private By summary_total_label = By.xpath("//div[@class='summary_info_label summary_total_label']");
	
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
	
	public String getSubtotalString() {
		try {
			return driver.findElement(summary_subtotal_label).getText().split(" ")[2].replace("$", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String getTaxString() {
		try {
			return driver.findElement(summary_tax_label).getText().split(" ")[1].replace("$", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String getTotalString() {
		try {
			return driver.findElement(summary_total_label).getText().split(" ")[1].replace("$", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public ProductListPage clickOnCancelButton() {
		driver.findElement(cancel_button).click();
		return new ProductListPage(driver);
	}
	
	public CompletedPage clickOnFinishButton() {
		driver.findElement(finish_button).click();
		return new CompletedPage(driver);
	}
	
}
