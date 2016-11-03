package biz.qh.automation.tests;

import static biz.qh.automation.utils.Log.logger;

import org.testng.annotations.Test;

import biz.qh.automation.page_objects.CasinoHomePage;
import biz.qh.automation.utils.Browsers;
import biz.qh.automation.utils.DriverFactory;
import biz.qh.automation.utils.SlotGame;

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
		logger.log(Level.INFO, "ecasinoPage is openned");
		ecasinoPage.closeBonusGameWindow();
		logger.log(Level.INFO, "Bonus game window is closed ");
		ecasinoPage.startSlotGameDemo(SlotGame.OCEAN_LEGENDS);
		logger.log(Level.INFO, "Started Ocen Legends Demo ");
	}

	@BeforeMethod
	@Parameters("browser")
	public void openLoginPage(String browser) {
		driver = DriverFactory.getDriver(Browsers.valueOf(browser));
		driver.get(CasinoHomePage.URL);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void logOut() {
		// driver.quit();
	}

}
