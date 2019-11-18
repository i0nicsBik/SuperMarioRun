package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import com.supermariorun.main.playerProgress;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import starter.GButton;

public class CharacterPane extends GraphicsPane implements ActionListener{
	private mainSMR program;
	private playerProgress progress;
	public static final String IMG_FOLDER = "characterPane/";
	public static final String lABEL_FONT = "Arial-Bold-22";
	private GImage BackButton;
	private GImage BackPipe;
	private GImage yoshiBubble;
	private GImage princessBubble;
	private GImage LuigiPic;
	private GImage PrincessPic;
	private GImage background;
	private GImage backLabel;
	private int count;
	private GImage coin;
	public Timer bubbleTimer;
	private static int PrincessCost = 50;
	private static int yoshiCost = 50;
	private GImage princessLabel;
	private GImage yoshiLabel;
	private GLabel coinCount;
	final double labelWidth;
	final double labelHeight;
	
	public CharacterPane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
		this.progress = program.getProgress();
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		labelWidth = mainWidth/12;
		labelHeight = mainHeight/12;
		
		bubbleTimer = new Timer(500, this);
	
		yoshiBubble = new GImage(IMG_FOLDER + "bubble.png",690,450);
		yoshiBubble.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		
		LuigiPic = new GImage(IMG_FOLDER + "Luigi.png", 675, 200);
		
		PrincessPic = new GImage(IMG_FOLDER + "Princess.png", 325, 200);
		PrincessPic.setSize(185, 250);
		
		princessBubble = new GImage(IMG_FOLDER + "bubble.png",335,450);
		princessBubble.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		
		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);
		
		background = new GImage(IMG_FOLDER + "background.jpg", 0, 0);
		background.setSize(mainWidth, mainHeight);
		
		backLabel = new GImage(IMG_FOLDER + "backLabel.png",170, 75);
		backLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		
		princessLabel = new GImage(IMG_FOLDER + "buyLabel.png",340,490);
		princessLabel.setSize(labelWidth*1.5, labelHeight*1.5);
		
		yoshiLabel = new GImage(IMG_FOLDER + "buyLabel.png",700, 490);
		yoshiLabel.setSize(labelWidth * 1.5, labelHeight * 1.5);
		
		coinCount = new GLabel("Coins: " + progress.getNumCoins());
		coinCount.setLocation(400, 100);
		coinCount.setFont(lABEL_FONT);
		
		coin = new GImage(IMG_FOLDER + "coin.gif", 320,50);
		coin.setSize(100, 100);
	}
	
	@Override
	public void showContents() {
		bubbleTimer.start();
		coinCount.setLabel("Coins: " + progress.getNumCoins());
		program.add(background);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(LuigiPic);
		program.add(PrincessPic);
		program.add(yoshiBubble);
		program.add(princessBubble);
		program.add(backLabel);
		program.add(princessLabel);
		program.add(yoshiLabel);
		program.add(coinCount);
		program.add(coin);
	}

	@Override
	public void hideContents() {
		bubbleTimer.stop();
		program.remove(background);
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(LuigiPic);
		program.remove(PrincessPic);
		program.remove(yoshiBubble);
		program.remove(princessBubble);
		program.remove(backLabel);
		program.remove(princessLabel);
		program.remove(yoshiLabel);
		program.remove(coinCount);
		program.remove(coin);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton || obj == backLabel || obj == BackPipe) {
			program.playPipeSound();
			program.switchToShop();
		}
		
		if(progress.isYoshiUnlocked() && (obj == yoshiBubble || obj == yoshiLabel)) {
			if (progress.isPrincessUnlocked()) {
				princessLabel.setImage(IMG_FOLDER + "equipButton.png");
				princessLabel.setSize(labelWidth*1.3, labelHeight*1.3);
			}
			yoshiLabel.setImage(IMG_FOLDER + "equippedButton.png");
			yoshiLabel.setSize(labelWidth*1.3, labelHeight*1.3);
			progress.setCurrentCharacter("Yoshi");
		}
		
		if(progress.isPrincessUnlocked() && (obj == princessBubble || obj == princessLabel)) {
			if (progress.isYoshiUnlocked()) {
				yoshiLabel.setImage(IMG_FOLDER + "equipButton.png");
				yoshiLabel.setSize(labelWidth*1.3, labelHeight*1.3);
			}
			princessLabel.setImage(IMG_FOLDER + "equippedButton.png");
			
			princessLabel.setSize(labelWidth*1.3, labelHeight*1.3);
			progress.setCurrentCharacter("Princess");
		}
		
		if(!progress.isYoshiUnlocked() && (obj == yoshiBubble || obj == yoshiLabel)) {
			YoshiTransaction();
			yoshiLabel.setImage(IMG_FOLDER + "equipButton.png");
			yoshiLabel.setSize(labelWidth*1.3, labelHeight*1.3);
		}
		
		if(!progress.isPrincessUnlocked() && (obj == princessBubble || obj == princessLabel)) {
			PrincessTransaction();
			princessLabel.setImage(IMG_FOLDER + "equipButton.png");
			princessLabel.setSize(labelWidth*1.3, labelHeight*1.3);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			princessBubble.move(0, 10);
			yoshiBubble.move(0, 10);
			backLabel.move(0, 10);
			BackButton.move(0, 10);
			princessLabel.move(0, 10);
			yoshiLabel.move(0,10);
		}
		
		if (count == 2) {
			princessBubble.move(0, -10);
			yoshiBubble.move(0, -10);
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			princessLabel.move(0, -10);
			yoshiLabel.move(0,-10);
			count = 0;
		}
		count++;
	}
	
	public void YoshiTransaction() {
		progress.getNumCoins();
		if(progress.getNumCoins() >= yoshiCost) {
			progress.setYoshiUnlocked();
			progress.decreaseCoins(yoshiCost);
			System.out.println("Purchase comfirmed");
			System.out.println(progress.getNumCoins());
			System.out.println("Yoshi Unlocked");
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
		
		else if (progress.getNumCoins() < yoshiCost) {
			System.out.println("not enough money");
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
	}
	
	public void PrincessTransaction() {
		if(progress.getNumCoins() >= PrincessCost) {
			progress.setPrincessUnlocked();
			progress.decreaseCoins(PrincessCost);
			System.out.println("Purchase comfirmed!");
			System.out.println("Princess Unlocked");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
		
		else if (progress.getNumCoins() < PrincessCost) {
			System.out.println("not enough money");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
	}	
}