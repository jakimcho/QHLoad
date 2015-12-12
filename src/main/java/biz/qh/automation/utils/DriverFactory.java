package biz.qh.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public abstract class DriverFactory {
	public static WebDriver getDriver(Browsers brower) {

		WebDriver driver;
		switch (brower) {
		case HTML:
			driver = new HtmlUnitDriver(); 
			System.out.println("Running HTMLDriver");
			break;
		case FIREFOX:
			System.out.println("Running FirefoxDriver");
			driver = new FirefoxDriver(); break;
		default:
			System.out.println("Running FirefoxDriver");
			driver = new FirefoxDriver(); break;
		}

		return driver;
	}

}
