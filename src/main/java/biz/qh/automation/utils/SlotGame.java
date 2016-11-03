package biz.qh.automation.utils;

public enum SlotGame { 
	OCEAN_LEGENDS("Ocean Legends"),
	HORSE_RACE("Horse Race")
	;
	
	private final String name;

	private SlotGame(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public String getSlotXpath(){
		return "//*[@gamename='" + this.name + "']";
	}

}
