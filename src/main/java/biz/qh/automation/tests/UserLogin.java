package biz.qh.automation.tests;

import org.testng.annotations.Test;

import biz.qh.automation.page_objects.LoginPage;
import biz.qh.automation.utils.Browsers;
import biz.qh.automation.utils.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class UserLogin {
	private WebDriver driver;

	@Test(invocationCount = 1, threadPoolSize = 1)
	public void userLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logIn("Administrator", "admin");
	}

	@BeforeMethod
	@Parameters("browser")
	public void openLoginPage(String browser) {
		driver = DriverFactory.getDriver(Browsers.valueOf(browser));
		driver.get(LoginPage.URL);
	}

	@AfterMethod
	public void logOut() {
		driver.quit();
	}

}
