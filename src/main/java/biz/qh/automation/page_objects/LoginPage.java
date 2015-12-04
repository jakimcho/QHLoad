package biz.qh.automation.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public static final String URL="http://qh.test.sf.archimed.bg/web/eprocess/login.aspx";
	
	private WebDriver driver;
	private WebElement loginForm;
	private WebElement userName;
	private WebElement userPassword;
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		this.loginForm = driver.findElement(By.id("ctl00_cph_MainContent_ctl00_PnlLogin"));
		this.userName = this.loginForm.findElement(By.id("ctl00_cph_MainContent_ctl00_TxtUserName"));
		this.userPassword = this.loginForm.findElement(By.id("ctl00_cph_MainContent_ctl00_TxtPassword"));
		this.loginButton = this.loginForm.findElement(By.id("ctl00_cph_MainContent_ctl00_BtnLogin"));
	}
	
	public UserHomePage logIn(String userName, String password){
		this.userName.sendKeys(userName);
		this.userPassword.sendKeys(password);
		this.loginButton.click();
		
		if (driver.getTitle().equals(UserHomePage.TITLE)){
			return new UserHomePage();
		}
		
		return null;
	}

}
