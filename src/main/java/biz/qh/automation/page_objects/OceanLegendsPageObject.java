package biz.qh.automation.page_objects;

import org.openqa.selenium.WebDriver;

public class OceanLegendsPageObject extends SlotGamePageObject {

	public OceanLegendsPageObject(WebDriver driver) {
		super (driver);
		this.imageDir = super.imageDir + "ocean_legends/";
	}

}
