package biz.qh.automation.tests;

import static biz.qh.automation.utils.Log.logger;

import org.testng.annotations.Test;

import biz.qh.automation.page_objects.CasinoHomePage;
import biz.qh.automation.utils.Browsers;
import biz.qh.automation.utils.DriverFactory;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class UserLogin {
	private WebDriver driver;

	@Test(invocationCount = 1, threadPoolSize = 1)
	public void closeBonusGame() {
		CasinoHomePage ecasinoPage = new CasinoHomePage(driver);
		logger.log(Level.ALL, "ecasinoPage is openned");
		ecasinoPage.closeBonusGameWindow();
		logger.log(Level.ALL, "Bonus game window is closed ");
	}

	@BeforeMethod
	@Parameters("browser")
	public void openLoginPage(String browser) {
		driver = DriverFactory.getDriver(Browsers.valueOf(browser));
		driver.get(CasinoHomePage.URL);
	}

	@AfterMethod
	public void logOut() {
		// driver.quit();
	}

}
