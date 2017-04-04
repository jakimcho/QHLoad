package biz.qh.automation.utils;

import org.sikuli.script.Pattern;

public enum GameElements {
	START_BUTTON("#buttonStart", "./assets/slot_games/start_button.png"),
	PASS_BUTTON("#buttonStart", "./assets/slot_games/pass_button.png"),
	STOP_BUTTON("#buttonStart", "./assets/slot_games/stop_button.png"),
	INFO_BUTTON("#btnMenuHelp", "./assets/slot_games/menu_help_button.png"),
	GAME_MENU_BUTTON("#buttonGameMenu", "./assets/slot_games/game_menu_button.png"),
	MULTIPLY_BETTING_BUTTON("#buttonGameMenu", "./assets/slot_games/x2_button.png"),
	// elements with no sikuli patterns
	ENTER_WITH_25_BUTTON("#left_lines>#q_stepL0"),
	ENTER_WITH_20_BUTTON("#left_lines>#q_stepL1"),
	ENTER_WITH_15_BUTTON("#left_lines>#q_stepL2"),
	ENTER_WITH_10_BUTTON("#left_lines>#q_stepL3"),
	ENTER_WITH_5_BUTTON("#left_lines>#q_stepL4"),
	SOUND_YES_BUTTON("#withSound"),
	SOUND_NO_BUTTON("#withoutSound"), 
	// Non clickable element. Used to retrieve data from
	FRAME_CREDIT("#frameCredit>.string1"), 
	FRAME_TOTAL_BET("#frameTotalBet>.string1"),
	FRAME_WIN("#frameWinnerPaid>.string1"),
	FREAME_MSG_BAR("#frameMsgBar>.msg_string1"),
	// Non clickable elements with patterns
	GAME_TITLE("#Top", "game_title.png");
	
	
	private String cssSelector;
	private String patternName;
	
	GameElements(String cssSelector, String ptnPath){
		this(cssSelector);
		this.patternName = ptnPath;
	}
	
	GameElements(String cssSelector){
		this.cssSelector = cssSelector;
	}

	public String getCssSelector() {
		return cssSelector;
	}

	public Pattern getSikulyPtn() {
		return new Pattern(patternName);
	}
	
	public Pattern getSikulyPtn(String specificGameDir) {
		return new Pattern(specificGameDir + patternName);
	}

}
