package biz.qh.automation.page_objects;

import static biz.qh.automation.utils.Log.logger;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import biz.qh.automation.utils.SlotGame;

public class CasinoHomePage {
	public static final String URL = "https://www.ecasino.bg/";

	private WebDriver driver;
	private WebElement bonusGameWindow;
	private WebElement closeBonusGameButton;
	private WebElement slotGamesContainer;

	public CasinoHomePage(WebDriver driver) {
		logger.log(Level.INFO, "Initializing CassinoHomePage");
		this.driver = driver;

		this.bonusGameWindow = this.driver.findElement(By.className("easypayad"));
		logger.log(Level.INFO, "Got element bonusGameWindow:");
		this.closeBonusGameButton = this.driver.findElement(By.cssSelector(".easypayad > .frlXX"));
		logger.log(Level.INFO, "Got element closeBonusGameButton:");
		this.slotGamesContainer = this.driver.findElement(By.cssSelector("ul#slots_"));
	}

	public void closeBonusGameWindow() {
		this.closeBonusGameButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.log(Level.INFO, "State of the Bonus Game Window: " + this.bonusGameWindow.isDisplayed());
		Assert.assertTrue(!this.bonusGameWindow.isDisplayed(), "Bonus game window is still displayed");
	}

	public void startSlotGameDemo(SlotGame game) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String winHandleBefore = driver.getWindowHandle();
		WebElement slotGame = this.slotGamesContainer.findElement(By.xpath(game.getSlotXpath()));
		WebElement slotGameDemoBtn = slotGame.findElement(By.cssSelector("a.freeBtn"));

		// Actions actions = new Actions(driver);
		// actions.moveToElement(slotGameDemoBtn).click();

		/*
		 * The commented lines above did not work. What I have found out is that
		 * if a link contains js/jquery webdriver sees it as a non-clickable
		 * element The solution is to use JavaScriptExecuter to
		 */
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", slotGameDemoBtn);

		logger.log(Level.INFO, "Demo Button enabled - " + slotGameDemoBtn.isEnabled());
		logger.log(Level.INFO,
				"Button tag is : " + slotGameDemoBtn.getTagName() + " and text is " + slotGameDemoBtn.getText());

		// Switch to new opened window
		for (String winHandle : driver.getWindowHandles()) {
			logger.log(Level.INFO, "This Driver title is  - " + driver.getTitle());
			if (!winHandle.equals(winHandleBefore)) {
				this.driver.switchTo().window(winHandle);
				logger.log(Level.INFO, "The new Driver title is  - " + driver.getTitle());
			}
		}

		WebElement noSoundBtn = null;

		logger.log(Level.INFO, "Switching frames  - " + driver.getTitle());
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));

		logger.log(Level.INFO, "Searching no sound button in Driver with title  - " + driver.getTitle());
		noSoundBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("withoutSound")));
		logger.log(Level.INFO, "No sound Button enabled - " + noSoundBtn.isEnabled());
		noSoundBtn.click();
	}

}
