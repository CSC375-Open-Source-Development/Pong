package Models;

import java.awt.Color;
import java.awt.Graphics2D;

import SwingShapes.Rectangle;

public class Ball {
	private Rectangle ball;
	private Direction xDirection;
	private Direction yDirection;
	private int ballSpeed = 2;
	
	public Ball() {
		ball = new Rectangle();
		ball.setColor(Color.white);
		ball.setSize(10,  10);
		xDirection = Direction.RIGHT;
		yDirection = Direction.UP;
	}
	
	public int getXLocation() {
		return ball.getXLocation();
	}
	
	public void setXLocation(int xLocation) {
		ball.setLocation(xLocation, ball.getYLocation());
	}
	
	public int getYLocation() {
		return ball.getYLocation();
	}
	
	public void setYLocation(int yxLocation) {
		ball.setLocation(ball.getXLocation(), yxLocation);
	}
	
	public Direction getXDirection() {
		return xDirection;
	}

	public void setXDirection(Direction xDirection) {
		this.xDirection = xDirection;
	}

	public Direction getYDirection() {
		return yDirection;
	}

	public void setYDirection(Direction yDirection) {
		this.yDirection = yDirection;
	}

	public void update() {
		ball.setLocation(ball.getXLocation() + xDirection.getVelocity() * ballSpeed, ball.getYLocation() + yDirection.getVelocity() * ballSpeed);
	}
	
	public void draw(Graphics2D g) {
		ball.paint(g);
	}
}
