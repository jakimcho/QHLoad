package biz.qh.automation.page_objects;

import static biz.qh.automation.utils.Log.logger;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import biz.qh.automation.utils.GameElements;
import biz.qh.automation.utils.ScreenShots;

/**
 * TODO: DRY clicking buttons this class has different methods for clicking
 * buttons which methods have one and the same code Need to create one general
 * click method which will take a parameter the button that need to be clicked
 */

public class SlotGamePageObject {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Screen screen;
	protected String imageDir = "./assets/slot_games/";

	public SlotGamePageObject(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
		this.screen = new Screen();
	}

	public void playWithSound(boolean soundOn) {
		WebElement buttonToClick = null;
		logger.log(Level.INFO, "Switching frames  - " + driver.getTitle());
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));

		GameElements element = soundOn ? GameElements.SOUND_YES_BUTTON : GameElements.SOUND_NO_BUTTON;
		buttonToClick = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element.getCssSelector())));

		logger.log(Level.INFO, "Clicking on Sound " + buttonToClick.getText() + " Button");
		buttonToClick.click();
	}

	public void clickElement(GameElements element) {
		WebElement webElement = null;
		webElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(element.getCssSelector())));
		logger.log(Level.INFO, element + "element displayed " + webElement.isDisplayed());

		ScreenShots.takeScreenShot(driver);

		logger.log(Level.INFO, "Clicking on the " + element);
		webElement.click();
	}

	public void waitElementToDisplay(GameElements element) {
		try {
			logger.log(Level.INFO, "Sikuli is checking for " + element + " element to be displayed");
			this.screen.wait(element.getSikulyPtn().exact(), 15);
		} catch (FindFailed e) {
			logger.log(Level.INFO, "Sikuli was not able to locate " + element + " - trace -" + e.getMessage());
		}
	}

	public boolean isElementDeisplayed(Pattern ptn) {
		try {
			logger.log(Level.INFO, "Sikuli is checking for " + ptn.getFilename() + " element to be displayed");
			this.screen.wait(ptn, 15);
			return true;
		} catch (FindFailed e) {
			logger.log(Level.INFO, "Sikuli was not able to locate " + ptn.getFilename() + " - trace -" + e.getMessage());
			return false;
		}
	}

	public void clickElement(GameElements element, boolean waitForElement) {
		waitElementToDisplay(element);
		clickElement(element);
	}

	public int currentCreditSize() {
		WebElement element = this.driver.findElement(By.cssSelector(GameElements.FRAME_CREDIT.getCssSelector()));
		return Integer.valueOf(element.getText());
	}

	public int finalBetSize() {
		WebElement element = this.driver.findElement(By.cssSelector(GameElements.FRAME_TOTAL_BET.getCssSelector()));
		return Integer.valueOf(element.getText());
	}

	public int winSize() {
		WebElement element = this.driver.findElement(By.cssSelector(GameElements.FRAME_WIN.getCssSelector()));
		return Integer.valueOf(element.getText());
	}

	public boolean isTitleDisplayed() {
		return isElementDeisplayed(GameElements.GAME_TITLE.getSikulyPtn(this.imageDir));
	}

	public boolean hasLine() {
		WebElement msgBat = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector(GameElements.FREAME_MSG_BAR.getCssSelector())));
		try {
			while (msgBat.getText().contains("УСПЕХ!")) {
				Thread.sleep(500);
			}
			
			return msgBat.getText().contains("ЛИНИЯ ");
		} catch (InterruptedException e) {
			logger.log(Level.INFO, "Interuption happend while thread sleeped " + e.getMessage());
			return false;
		}
	}

}
