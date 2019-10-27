package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GObject;
import starter.GButton;

public class ShopPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton ShopButton;

	public ShopPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		ShopButton = new GButton("Shop", 200, 200, 200, 200);
		ShopButton.setFillColor(Color.RED);
	}

	@Override
	public void showContents() {
		program.add(ShopButton);
	}

	@Override
	public void hideContents() {
		program.remove(ShopButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == ShopButton) {
			
		}
	}
}
