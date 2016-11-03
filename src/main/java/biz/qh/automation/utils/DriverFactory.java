package biz.qh.automation.utils;

import java.util.logging.Level;
import static biz.qh.automation.utils.Log.logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



public abstract class DriverFactory {
	public static WebDriver getDriver(Browsers brower) {

		WebDriver driver;
		switch (brower) {
		case HTML:
			driver = new PhantomJSDriver(); 
			System.out.println("Running HTMLDriver");
			break;
		case FIREFOX:
			logger.log(Level.INFO, "Running FirefoxDriver");
			System.setProperty("webdriver.gecko.driver", "D:/webdrivers/FF/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", false);
			driver = new FirefoxDriver(capabilities); 
			break;
		default:
			System.out.println("Running FirefoxDriver");
			driver = new FirefoxDriver(); break;
		}

		return driver;
	}

}
