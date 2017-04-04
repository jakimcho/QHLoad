package biz.qh.automation.tests;

import static biz.qh.automation.utils.Log.logger;

import org.testng.annotations.Test;

import biz.qh.automation.page_objects.CasinoHomePage;
import biz.qh.automation.page_objects.OceanLegendsPageObject;
import biz.qh.automation.utils.Browsers;
import biz.qh.automation.utils.DriverFactory;
import biz.qh.automation.utils.GameElements;
import biz.qh.automation.utils.SlotGame;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class OceanLegendsGameTest {
	private WebDriver driver;

	@Test
	public void StartGame() {
		CasinoHomePage ecasinoPage = new CasinoHomePage(driver);
		logger.log(Level.INFO, "ecasinoPage is openned");
		if (ecasinoPage.hasBonusWindow()) {
			ecasinoPage.closeBonusGameWindow();
			logger.log(Level.INFO, "Bonus game window is closed ");
		}
		
		OceanLegendsPageObject oceanLegendsGame = (OceanLegendsPageObject) ecasinoPage.startSlotGameDemo(SlotGame.OCEAN_LEGENDS);
		logger.log(Level.INFO, "Started Ocen Legends Demo ");
		oceanLegendsGame.playWithSound(false);
		oceanLegendsGame.waitElementToDisplay(GameElements.START_BUTTON);
		assertTrue(oceanLegendsGame.currentCreditSize() == 30000, "Expected initial credit value 30000");
		assertTrue(oceanLegendsGame.finalBetSize() == 25, "Expected initial final bet value 25");
		assertTrue(oceanLegendsGame.isTitleDisplayed(), "Expected game title to be Ocean Legends");
	}
	
	@Test
	public void PlayGameToWin(){
		CasinoHomePage ecasinoPage = new CasinoHomePage(driver);
		logger.log(Level.INFO, "ecasinoPage is openned");
		if (ecasinoPage.hasBonusWindow()) {
			ecasinoPage.closeBonusGameWindow();
			logger.log(Level.INFO, "Bonus game window is closed ");
		}
		
		OceanLegendsPageObject oceanLegendsGame = (OceanLegendsPageObject) ecasinoPage.startSlotGameDemo(SlotGame.OCEAN_LEGENDS);
		logger.log(Level.INFO, "Started Ocen Legends Demo ");
		oceanLegendsGame.playWithSound(false);
		while(!oceanLegendsGame.hasLine()){
			oceanLegendsGame.clickElement(GameElements.START_BUTTON);
		}
		
		oceanLegendsGame.clickElement(GameElements.PASS_BUTTON, true);
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
		driver.quit();
	}

}
