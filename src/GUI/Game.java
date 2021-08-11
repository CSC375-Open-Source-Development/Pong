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

public class Game extends JPanel {
	private Timer timer;
	private Ball ball;

	public Game() {
		Window.setTitle("Snake");
		setDoubleBuffered(true);

		ball = new Ball();

		timer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ball.update();

				repaint();
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {

				}
				if (e.getKeyCode() == KeyEvent.VK_D) {

				}
				if (e.getKeyCode() == KeyEvent.VK_W) {

				}
				if (e.getKeyCode() == KeyEvent.VK_S) {

				}
			}

		});

		this.setFocusable(true);

	}

	public void startGame() {
		timer.start();
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		ball.draw(brush);
	}

}
