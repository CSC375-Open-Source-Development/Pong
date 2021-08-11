package Models;

import java.awt.Color;
import java.awt.Graphics2D;

import SwingShapes.Rectangle;

public class Paddle {
	private Rectangle paddle;
	private int speed = 2;
	
	public Paddle() {
		paddle = new Rectangle();
		paddle.setSize(10, 60);
		paddle.setColor(Color.white);
	}
	
	public int getXLocation() {
		return paddle.getXLocation();
	}
	
	public void setXLocation(int xLocation) {
		paddle.setLocation(xLocation, paddle.getYLocation());
	}
	
	public int getYLocation() {
		return paddle.getYLocation();
	}
	
	public void setYLocation(int yLocation) {
		paddle.setLocation(paddle.getXLocation(), yLocation);
	}
	
	public int getWidth() {
		return paddle.getWidth();
	}
	
	public int getHeight() {
		return paddle.getHeight();
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void moveUp() {
		paddle.setLocation(paddle.getXLocation(), paddle.getYLocation() + Direction.UP.getVelocity() * speed);
	}
	
	public void moveDown() {
		paddle.setLocation(paddle.getXLocation(), paddle.getYLocation() + Direction.DOWN.getVelocity() * speed);
	}
	
	public void draw(Graphics2D g) {
		paddle.paint(g);
	}
	
}
