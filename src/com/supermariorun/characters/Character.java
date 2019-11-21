package com.supermariorun.characters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import com.supermariorun.main.mainSMR;
import com.supermariorun.panes.LevelPane;
import com.supermariorun.panes.LevelPaneDev;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class Character extends GraphicsProgram implements ActionListener {
	private mainSMR program;
	private LevelPane levelPane;
	private LevelPaneDev levelPaneDev;
	private GImage characImg;
	private GObject leftFoot;
	private GObject rightFoot;
	public static final String IMG_FOLDER = "character/";
	private static String STAR_EXT = "";
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Coins;
	public boolean jumpUpState;
	private String character = "mario";
	private int jumpCount = 0;
	private int starCount = 0;
	private Timer starTimer;
	
	public Character(mainSMR mainSMR, LevelPane levelPane) {
		program = mainSMR;
		this.levelPane = levelPane;
		character = program.getProgress().getCurrentCharacter();
		characImg = new GImage (IMG_FOLDER + STAR_EXT + character + "Stand.png", 100, 520); 
		characImg.setSize(64, 64);
		starTimer = new Timer (1000, this);
		Environment = levelPane.getLevel().getEnvironment();
		Coins = levelPane.getLevel().getCoins();
	}

	public Character(mainSMR mainSMR, LevelPaneDev levelPaneDev) {
		program = mainSMR;
		this.levelPaneDev = levelPaneDev;
		characImg = new GImage (IMG_FOLDER  + STAR_EXT + character + "Stand.png", 100, 520); 
		characImg.setSize(64, 64);
	}

	public void setStarMode() {
		STAR_EXT = "star";
		starTimer.start();
		program.stopLvlOneTrack();
		program.playStarTrack();
	}
	
	public void pauseStarMode() {
		starTimer.start();
		program.pauseStarTrack();
	}
	
	public void stand() {
		characImg.setImage(IMG_FOLDER  + STAR_EXT + character + "Stand.png");
	}

	public void run() {
		characImg.setImage(IMG_FOLDER  + STAR_EXT + character + "Run.gif");
	}
	
	public void jump() {
		characImg.setImage(IMG_FOLDER  + STAR_EXT + character + "Jump.gif");
		leftFoot = program.getElementAt(characImg.getX() + 20, characImg.getY() + 80);
		rightFoot = program.getElementAt(characImg.getX() + 60, characImg.getY() + 80);
		
		jumpUpState = false;
			
		if (jumpCount >=  0 && jumpCount < 6) {
			jumpUpState = true;
			setJumpCount(jumpCount + 1);
		}
			
		if  (jumpCount > 6) {
			jumpUpState = false;
		}
	
		if (jumpUpState) {		
			characImg.move(0, -20);
		}
			
		if (!jumpUpState) {
			characImg.move(0, 20);				
			for (GImage obj : Environment) {
				if (leftFoot == obj || rightFoot == obj) {
					
					levelPane.jumpState = false;
					run();
				}
			}
		}
	}
	
	public void collectCoin() {
		for (Iterator<GImage> it = Coins.iterator(); it.hasNext(); ) {
			GImage img = it.next();
			if (characImg.getBounds().intersects(img.getBounds())) {
				program.playCoinEffect();
				program.remove(img);
				it.remove();
				program.getProgress().incrementCoins();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		starCount++;
		
		if (starCount == 10) {
			starTimer.stop();
			starCount = 0;
			STAR_EXT = "";
			program.getProgress().clearCurrentPowerUp();
			program.getProgress().resetStarPurchased();
			program.stopStarTrack();
			program.playLvlOneTrack();
			run();
		}
	}
	
	public GImage getCharacter(){
		return characImg;
	}

	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}

	public void reset() {
		characImg.setLocation(100, 520);		
	}
		
}
