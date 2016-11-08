package biz.qh.automation.page_objects;

import static biz.qh.automation.utils.Log.logger;

import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import biz.qh.automation.utils.SlotGame;
import biz.qh.automation.utils.SlotGamePageObjectFactory;

public class CasinoHomePage {
	public static final String URL = "https://www.ecasino.bg/";

	private WebDriver driver;
	private WebElement bonusGameWindow;
	private WebElement closeBonusGameButton;
	private WebElement slotGamesContainer;

	public CasinoHomePage(WebDriver driver) {
		logger.log(Level.INFO, "Initializing CassinoHomePage");
		this.driver = driver;
		List<WebElement> tempWindows = this.driver.findElements(By.className("easypayad"));
		this.slotGamesContainer = this.driver.findElement(By.cssSelector("ul#slots_"));
		
		if (tempWindows.size() > 0) {
			logger.log(Level.INFO, "Got element bonusGameWindow:");
			this.closeBonusGameButton = tempWindows.get(0);
		}
	}

	public void closeBonusGameWindow() {
		this.closeBonusGameButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.log(Level.INFO, "State of the Bonus Game Window: " + this.bonusGameWindow.isDisplayed());
		Assert.assertTrue(!this.bonusGameWindow.isDisplayed(), "Bonus game window is still displayed");
	}

	public SlotGamePageObject startSlotGameDemo(SlotGame game) {
		String winHandleBefore = driver.getWindowHandle();
		WebElement slotGame = this.slotGamesContainer.findElement(By.xpath(game.getSlotXpath()));
		WebElement slotGameDemoBtn = slotGame.findElement(By.cssSelector("a.freeBtn"));

		/*
		 * The commented lines above did not work. What I have found out is that
		 * if a link contains js/jquery webdriver sees it as a non-clickable
		 * element The solution is to use JavaScriptExecuter to
		 */
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", slotGameDemoBtn);

		// Switch to the new opened window
		for (String winHandle : driver.getWindowHandles()) {
			logger.log(Level.INFO, "This Driver title is  - " + driver.getTitle());
			if (!winHandle.equals(winHandleBefore)) {
				this.driver.switchTo().window(winHandle);
				logger.log(Level.INFO, "The new Driver title is  - " + driver.getTitle());
			}
		}
		
		return SlotGamePageObjectFactory.slotGamePageObject(game, this.driver);
	}

	public boolean hasBonusWindow() {
		return this.driver.findElements(By.className("easypayad")).size() > 0;
	}

}
