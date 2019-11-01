package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class ShopPane extends GraphicsPane {
	private mainSMR program; 
	public static final String IMG_FOLDER = "menuScreen/";
	private GButton PowerUpButton;
	private GButton CharacterButton;
	private GButton BackButton;
	private GImage BackPipe;
	private GButton InventoryButton;

	public ShopPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		PowerUpButton = new GButton("Power Up", 700, 500, 150, 100);
		PowerUpButton.setFillColor(Color.BLUE);
		CharacterButton = new GButton("Character", 200, 500, 150, 100);
		CharacterButton.setFillColor(Color.RED);
		BackButton = new GButton("Back", 100, 100, 80, 80);
		BackButton.setFillColor(Color.GREEN);
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png",mainSMR.getWidth()/7, mainSMR.getHeight()/1.3);
		BackPipe.setSize(mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		InventoryButton = new GButton("Inventory", 800, 100, 100, 100);
		InventoryButton.setFillColor(Color.YELLOW);
	}

	@Override
	public void showContents() {
		program.add(PowerUpButton);
		program.add(CharacterButton);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(InventoryButton);
	}

	@Override
	public void hideContents() {
		program.remove(PowerUpButton);
		program.remove(CharacterButton);
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(InventoryButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == PowerUpButton) {
			program.playPipeSound();
			program.switchToPowerUp();
		}
		
		if (obj == CharacterButton) {
			program.playPipeSound();
			program.switchToCharacter();
		}
		
		if (obj == InventoryButton) {
			program.playPipeSound();
			program.switchToInventory();
		}
		
		if (obj == BackButton) {
			program.playPipeSound();
			program.stopShopSound();
			program.playMenuSound();
			program.switchToMenu();
		}
	}
}
