package biz.qh.automation.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNewDocument {
	private WebDriver driver;
	private WebElement incomingDocument;
	private WebElement outgoingDocument;
	private WebElement internalDocument;
	private WebElement models;
	private WebElement processes;
	
	public CreateNewDocument(WebDriver driver){
		this.driver = driver;
		this.incomingDocument = driver.findElement(By.id("ctl00_cph_LeftContent_BtnIncoming"));
		this.outgoingDocument = driver.findElement(By.id("ctl00_cph_LeftContent_BtnOutgoing"));
		this.internalDocument = driver.findElement(By.id("ctl00_cph_LeftContent_BtnInternal"));
		this.models = driver.findElement(By.id("ctl00_cph_LeftContent_BtnModels"));
		this.processes = driver.findElement(By.id("ctl00_cph_LeftContent_BtnProcesses"));
	}
}
