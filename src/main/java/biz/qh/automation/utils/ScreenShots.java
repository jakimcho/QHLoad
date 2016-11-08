package biz.qh.automation.utils;

import static biz.qh.automation.utils.Log.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenShots {
	public static void takeScreenShot(WebDriver driver, String destination, String fileName) {
		// Taking Screen shot -> need to be extract to a util method
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File file = new File(destination.trim() + fileName.trim());
		try {
			logger.log(Level.INFO, "Saving screenshot to " + file.getAbsolutePath());
			FileUtils.copyFile(source, file);
		} catch (IOException e) {
			logger.log(Level.INFO, "Error saving screenshot " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void takeScreenShot(WebDriver driver) {
		String dir = "./" + driver.getTitle();
		String name = "/img_" + System.currentTimeMillis();
		takeScreenShot(driver, dir, name);
	}

}
