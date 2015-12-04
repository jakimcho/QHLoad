package biz.qh.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class DriverFactory {
	public static WebDriver getDriver(){
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

}
