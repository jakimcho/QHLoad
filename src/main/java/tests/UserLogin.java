package tests;

import org.testng.annotations.Test;

import page_objects.LoginPage;
import utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class UserLogin {
	private WebDriver driver;

	@Test(invocationCount = 5, threadPoolSize = 5)
	public void userLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logIn("Administrator", "admin");
	}

	@BeforeMethod
	public void openLoginPage() {
		driver = DriverFactory.getDriver();
		driver.get(LoginPage.URL);
	}

	@AfterMethod
	public void logOut() {
		driver.close();
	}

}
