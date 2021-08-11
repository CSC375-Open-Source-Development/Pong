package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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

	public Game() {
		Window.setTitle("Snake");
		setDoubleBuffered(true);

		ball = new Ball();
		paddlePlayer = new Paddle();
		paddleCpu = new Paddle();

		timer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ball.update();
				if (directionPressed != null) {
					if (directionPressed == Direction.UP) {
						paddlePlayer.moveUp();
					}
					if (directionPressed == Direction.DOWN) {
						paddlePlayer.moveDown();
					}
				}
				//paddleCpu.update();
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

}
