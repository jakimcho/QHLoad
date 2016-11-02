package biz.qh.automation.utils;

import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.io.IOException;
import java.util.logging.FileHandler;;

public class Log {
	public final static Logger logger = loggetSetUp();
	
	private static Logger loggetSetUp(){
		Logger tmpLogger = Logger.getAnonymousLogger();
		Handler fileHandler;
		
		try {
			fileHandler = new FileHandler("./automattion.log");
			tmpLogger.addHandler(fileHandler);
			tmpLogger.setLevel(Level.INFO);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tmpLogger;
	}
}
