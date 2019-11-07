package com.supermariorun.main;
import java.awt.event.ActionListener;
import com.supermariorun.panes.CharacterPane;
import com.supermariorun.panes.InstructionsPane;
import com.supermariorun.panes.InventoryPane;
import com.supermariorun.panes.MenuPane;
import com.supermariorun.panes.PowerUpPane;
import com.supermariorun.panes.ShopPane;
import com.supermariorun.panes.StartPane;
import com.supermariorun.panes.TourPane;
import com.supermariorun.soundeffects.AudioPlayer;

public class mainSMR extends GraphicsApplication implements ActionListener{
	public static final String MUSIC_FOLDER = "sounds";
	private static final String START_TRACK = "startTrack.mp3";
	private static final String MENU_TRACK = "menuTrack.mp3";
	private static final String TOUR_TRACK = "tourTrack.mp3";
	private static final String SHOP_TRACK = "shopTrack.mp3";
	private static final String GUIDE_TRACK = "guideTrack.mp3";
	private static final String PIPE_EFFECT = "pipeEffect.wav";
	private static final String JUMP_EFFECT = "jump.wav";
	private static final String LEVEL_EFFECT = "sm64_here_we_go.wav";
	
	private StartPane startPane;
	public MenuPane menuPane;
	private ShopPane shopPane;
	private PowerUpPane powerUpPane;
	private CharacterPane characterPane;
	private InstructionsPane instructionsPane;
	private InventoryPane inventoryPane;
	private TourPane tourPane;
	private playerProgress progress;

    protected static final int FONT_SIZE = 18;
    
    public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public AudioPlayer audio;

	public void run() {	
		audio = AudioPlayer.getInstance();
		progress = new playerProgress (this);
		startPane = new StartPane(this);
		menuPane = new MenuPane(this);
		tourPane = new TourPane(this);
		shopPane = new ShopPane(this);
		powerUpPane = new PowerUpPane (this);
		characterPane = new CharacterPane (this);
		instructionsPane = new InstructionsPane(this);
		inventoryPane = new InventoryPane(this);
		switchToStart();
	}
	
	public void switchToStart() {
		playStartSound();
		switchToScreen(startPane);
	}
	
	public void switchToMenu() {
		switchToScreen(menuPane);
	}

	public void switchToShop() {
		switchToScreen(shopPane);
	}
	
	public void switchToInstructions() {
		switchToScreen(instructionsPane);
	}
	
	public void switchToInventory() {
		switchToScreen(inventoryPane);
	}
	
	public void switchToTour()
	{
		switchToScreen(tourPane);
	}

	public void switchToPowerUp() {
		switchToScreen(powerUpPane);
	}

	public void switchToCharacter() {
		switchToScreen(characterPane);
	}
	
	public void playStartSound() {
		audio.playSound(MUSIC_FOLDER, START_TRACK, true);
	}
	
	public void stopStartSound() {
		audio.stopSound(MUSIC_FOLDER, START_TRACK);
	}
	
	public void playMenuSound() {
		audio.playSound(MUSIC_FOLDER, MENU_TRACK, true);
	}
	
	public void stopMenuSound() {
		audio.stopSound(MUSIC_FOLDER, MENU_TRACK);
	}
	
	public void playTourSound() {
		audio.playSound(MUSIC_FOLDER, TOUR_TRACK, true);
	}
	
	public void stopTourSound() {
		audio.stopSound(MUSIC_FOLDER, TOUR_TRACK);
	}
	
	public void playShopSound() {
		audio.playSound(MUSIC_FOLDER, SHOP_TRACK, true);
	}
	
	public void stopShopSound() {
		audio.stopSound(MUSIC_FOLDER, SHOP_TRACK);
	}
	
	public void playGuideSound() {
		audio.playSound(MUSIC_FOLDER, GUIDE_TRACK, true);
	}
	
	public void stopGuideSound() {
		audio.stopSound(MUSIC_FOLDER, GUIDE_TRACK);
	}
	
	public void playPipeSound() {
		audio.playSound(MUSIC_FOLDER, PIPE_EFFECT);
	}
	
	public void playJumpSound() {
		audio.playSound(MUSIC_FOLDER, JUMP_EFFECT);
	}
	
	public void playLevelSound() {
		audio.playSound(MUSIC_FOLDER, LEVEL_EFFECT);
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}	
}
