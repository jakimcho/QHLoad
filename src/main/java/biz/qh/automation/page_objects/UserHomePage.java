package biz.qh.automation.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserHomePage {

	public static final String TITLE = "Archimed eProcess.NET";
	private WebDriver driver;
	private WebElement registerNewDocument;
	private WebElement navHome;
	private WebElement navSearch;
	private WebElement navMyTasks;
	private WebElement navReports;
	private WebElement navSettings;
	
	public UserHomePage(WebDriver driver){
		this.driver = driver;
		this.registerNewDocument = driver.findElement(By.id("ctl00_link_NewDocument"));
		this.navHome = driver.findElement(By.id("btn_Home"));
		this.navSearch = driver.findElement(By.id("btn_Search"));
		this.navMyTasks = driver.findElement(By.id("btn_MyTasks"));
		this.navReports = driver.findElement(By.id("btn_Reports"));
		this.navSettings = driver.findElement(By.id("btn_Settings"));

	}
	
	public CreateNewDocument createNewDocument(){
		this.registerNewDocument.click();
		return new CreateNewDocument(driver);
	}
	
	
}
