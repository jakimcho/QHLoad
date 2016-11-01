package biz.qh.automation.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;

import biz.qh.automation.page_objects.*;
import static biz.qh.automation.utils.Log.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CasinoHomePage {
	public static final String URL="https://www.ecasino.bg/";
	
	private WebDriver driver;
	private WebElement bonusGameWindow;
	private WebElement closeBonusGameButton;
	private WebElement slotGamesContainer;
	
	public CasinoHomePage(WebDriver driver){
		logger.log(Level.ALL, "Initializing CassinoHomePage");
		this.driver = driver;
		
		this.bonusGameWindow = this.driver.findElement(By.className("easypayad"));
		logger.log(Level.ALL, "Got element bonusGameWindow:");
		this.closeBonusGameButton = this.driver.findElement(By.cssSelector(".easypayad > .frlXX"));
		logger.log(Level.ALL, "Got element closeBonusGameButton:");
		this.slotGamesContainer = this.driver.findElement(By.cssSelector("ul#slots_"));
	}
	
	public void closeBonusGameWindow(){
		this.closeBonusGameButton.click();
		
		Assert.assertTrue(!this.bonusGameWindow.isDisplayed(), "Bonus game window is still displayed");
	}

}
