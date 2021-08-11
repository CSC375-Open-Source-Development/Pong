package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import GUIControls.Window;
import Models.Ball;
import Models.Direction;
import Models.Paddle;

public class Game extends JPanel {
	private Timer timer;
	private Ball ball;
	private Paddle paddlePlayer;
	private Paddle paddleCpu;
	private Direction directionPressed;
	private Direction paddleCpuDirection;

	public Game() {
		Window.setTitle("Pong");
		setDoubleBuffered(true);

		ball = new Ball();
		paddlePlayer = new Paddle();
		paddleCpu = new Paddle();
		paddleCpuDirection = Direction.UP;

		timer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (directionPressed != null) {
					if (directionPressed == Direction.UP) {
						paddlePlayer.moveUp();
					} 
					else if (directionPressed == Direction.DOWN) {
						paddlePlayer.moveDown();
					}
				}

				if (paddleCpuDirection == Direction.UP) {
					if (paddleCpu.getYLocation() + Direction.UP.getVelocity() * paddleCpu.getSpeed() > 0) {
						paddleCpu.moveUp();
					}
					else {
						paddleCpuDirection = Direction.DOWN;
					}
				} 
				else if (paddleCpuDirection == Direction.DOWN) {
					if (paddleCpu.getYLocation() + paddleCpu.getHeight()
							+ Direction.DOWN.getVelocity() * paddleCpu.getSpeed() < getHeight()) {
						paddleCpu.moveDown();
					} 
					else {
						paddleCpuDirection = Direction.UP;
					}
				}
				
				ball.move();

				
				if (ball.getYDirection() == Direction.UP && ball.getYLocation() < 0) {
					ball.setYDirection(Direction.DOWN);
					ball.setYLocation(0);
				} 
				else if (ball.getYDirection() == Direction.DOWN && ball.getYLocation() + ball.getHeight() > getHeight()) { 
					ball.setYDirection(Direction.UP);
					ball.setYLocation(getHeight() - ball.getHeight());
				}
	
				if (isBallIntersectingPaddle(paddlePlayer, ball)) {
					if (ball.getXDirection() == Direction.LEFT) {
						ball.setXLocation(paddlePlayer.getXLocation() + paddlePlayer.getWidth());
						ball.setXDirection(Direction.RIGHT);
					}
					else if (ball.getXDirection() == Direction.RIGHT) {
						ball.setXLocation(paddlePlayer.getXLocation() - ball.getWidth());
						ball.setXDirection(Direction.LEFT);
					}
				}
				
				if (isBallIntersectingPaddle(paddlePlayer, ball)) {
					if (ball.getYDirection() == Direction.UP) {
						ball.setYLocation(paddlePlayer.getYLocation() + paddlePlayer.getHeight());
						ball.setYDirection(Direction.DOWN);
					}
					else if (ball.getYDirection() == Direction.DOWN) {
						ball.setYLocation(paddlePlayer.getYLocation());
						ball.setYDirection(Direction.UP);
					}
				}
				
				if (isBallIntersectingPaddle(paddleCpu, ball)) {
					if (ball.getXDirection() == Direction.LEFT) {
						ball.setXLocation(paddleCpu.getXLocation() + paddleCpu.getWidth());
						ball.setXDirection(Direction.RIGHT);
					}
					else if (ball.getXDirection() == Direction.RIGHT) {
						ball.setXLocation(paddleCpu.getXLocation() - ball.getWidth());
						ball.setXDirection(Direction.LEFT);
					}
				}
				
				if (isBallIntersectingPaddle(paddleCpu, ball)) {
					if (ball.getYDirection() == Direction.UP) {
						ball.setYLocation(paddleCpu.getYLocation() + paddleCpu.getHeight());
						ball.setYDirection(Direction.DOWN);
					}
					else if (ball.getYDirection() == Direction.DOWN) {
						ball.setYLocation(paddleCpu.getYLocation());
						ball.setYDirection(Direction.UP);
					}
				}
				
				if (ball.getXLocation() < 0 || ball.getXLocation() + ball.getWidth() > getWidth()) {
					System.exit(1);
				}

				repaint();
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					directionPressed = Direction.UP;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					directionPressed = Direction.DOWN;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (directionPressed == Direction.UP) {
						directionPressed = null;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (directionPressed == Direction.DOWN) {
						directionPressed = null;
					}
				}
			}

		});

		this.setFocusable(true);

	}

	public void startGame() {
		paddlePlayer.setXLocation(10);
		paddlePlayer.setYLocation(getHeight() / 2 - paddlePlayer.getHeight() / 2);
		paddleCpu.setXLocation(getWidth() - 10 - paddleCpu.getWidth());
		paddleCpu.setYLocation(getHeight() / 2 - paddlePlayer.getHeight() / 2);
		ball.setXLocation(getWidth() / 2 - ball.getWidth() / 2);
		ball.setYLocation(getHeight() / 2 - ball.getHeight() / 2);
		
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		fillBackground(brush, Color.black);
		paddlePlayer.draw(brush);
		paddleCpu.draw(brush);
		ball.draw(brush);
	}

	private void fillBackground(Graphics2D g, Color backgroundColor) {
		Color oldColor = g.getColor();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(oldColor);
	}
	
	private boolean isBallIntersectingPaddle(Paddle paddle, Ball ball) {
		return paddle.getXLocation() < ball.getXLocation() + ball.getWidth() && paddle.getXLocation() + paddle.getWidth() > ball.getXLocation() && 
			paddle.getYLocation() < ball.getYLocation() + ball.getHeight() && paddle.getYLocation() + paddle.getHeight() > ball.getYLocation();
	}
}
