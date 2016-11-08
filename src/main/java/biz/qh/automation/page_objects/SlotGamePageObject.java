package biz.qh.automation.page_objects;

import static biz.qh.automation.utils.Log.logger;

import java.io.File;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import biz.qh.automation.utils.ScreenShots;

/**
*   TODO: DRY clicking buttons
*   this class has different methods for clicking buttons
*   which methods have one and the same code
*   Need to create one general click method which will take a parameter
*   the button that need to be clicked
*/

public class SlotGamePageObject {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Screen screen;
	protected Pattern playButtonImgPtn;
	protected Pattern passButtonImgPtn;
	protected Pattern gameMenuButtonImgPtn;
	protected WebElement startButton;
	protected WebElement gameMenuButton;

	public SlotGamePageObject(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
		this.screen = new Screen();
		String filePath = new File("").getAbsolutePath();
		filePath.concat("path to the property file");
		logger.log(Level.INFO, "Current path  - " + filePath);
		
		this.playButtonImgPtn = new Pattern("./assets/slot_games/play_button.png");
		this.passButtonImgPtn = new Pattern("./assets/slot_games/pass_button.png");
		this.gameMenuButtonImgPtn = new Pattern("./assets/slot_games/game_menu_button.png");
	}

	public void playWithSound(boolean soundOn) {
		WebElement buttonToClick = null;
		logger.log(Level.INFO, "Switching frames  - " + driver.getTitle());
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		logger.log(Level.INFO, "Searching no sound button in Driver with title  - " + driver.getTitle());

		if (soundOn) {
			buttonToClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("withoutSound")));
		} else {
			buttonToClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("withSound")));
		}
		
		logger.log(Level.INFO, "Clicking on Sound " + buttonToClick.getText() + " Button");
		buttonToClick.click();
		
	}
	
	public void clickPlay(){
		try {
			logger.log(Level.INFO, "Sikuli is checking fot start button to be displayed");
			this.screen.wait(this.playButtonImgPtn.exact(), 5000);
			this.gameMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonStart")));
			logger.log(Level.INFO, "Start Button displayed " + gameMenuButton.isDisplayed());
			
			// Taking Screen shot -> need to be extract to a util method
			ScreenShots.takeScreenShot(driver);
			
			logger.log(Level.INFO, "Clicking on the game menu button ");
			this.gameMenuButton.click();
		} catch (FindFailed e) {
			logger.log(Level.INFO, "Sikuli was not able to locate game menu button" + e.getMessage());
			e.printStackTrace();	
		} 
	}
	
	public void clickPass(){
		try {
			logger.log(Level.INFO, "Sikuli is checking fot pass button to be displayed");
			this.screen.wait(this.passButtonImgPtn.exact(), 5000);
			this.startButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonStart")));
			logger.log(Level.INFO, "Pass Button displayed " + startButton.isDisplayed());
			
			// Taking Screen shot -> need to be extract to a util method
			ScreenShots.takeScreenShot(driver);
			
			logger.log(Level.INFO, "Clicking on the play button ");
			this.startButton.click();
		} catch (FindFailed e) {
			logger.log(Level.INFO, "Sikuli was not able to locate play button" + e.getMessage());
			e.printStackTrace();	
		} 
	}
	
	public void clickSetting(){
		try {
			logger.log(Level.INFO, "Sikuli is checking fot settings button to be displayed");
			this.screen.wait(this.gameMenuButtonImgPtn.exact(), 5000);
			this.startButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonGameMenu")));
			logger.log(Level.INFO, "Pass Button displayed " + startButton.isDisplayed());
			
			// Taking Screen shot -> need to be extract to a util method
			ScreenShots.takeScreenShot(driver);
			
			logger.log(Level.INFO, "Clicking on the play button ");
			this.startButton.click();
		} catch (FindFailed e) {
			logger.log(Level.INFO, "Sikuli was not able to locate play button" + e.getMessage());
			e.printStackTrace();	
		} 
	}

}
