package biz.qh.automation.utils;

import org.openqa.selenium.WebDriver;

import biz.qh.automation.page_objects.OceanLegendsPageObject;
import biz.qh.automation.page_objects.SlotGamePageObject;

public class SlotGamePageObjectFactory {

	public static SlotGamePageObject slotGamePageObject(SlotGame game, WebDriver driver) {
		SlotGamePageObject slotGame = null;
		switch (game) {
		case OCEAN_LEGENDS:
			slotGame = new OceanLegendsPageObject(driver);
			break;
		default:
			break;
		}
		return slotGame;
	}

}
