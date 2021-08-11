package Models;

import java.awt.Color;
import java.awt.Graphics2D;

import SwingShapes.Rectangle;

public class Ball {
	private Rectangle ball;
	
	public Ball() {
		ball.setColor(Color.white);
		ball.setSize(10,  10);
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		ball.paint(g);
	}
}
