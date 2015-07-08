/**
 * Author: Ashwin Kamalakannan
 * Intersection Assignment
 * last edited: 10/04/2015
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Intersection extends JPanel {

	final static int frameX = 640, frameY = 600;
	private Car[] car;
	private boolean light; // true when vertical is green
	private final static int timerConstant = 140;
	private static int yTimer = timerConstant;
	private static int spawnX, spawnY;

	public Intersection() {
		car = new Car[16];
		for (int i = 0; i < car.length; i++) {
			if (i / 4 == 0) {
				spawnX = frameX / 2 + 30;
				spawnY = frameY / 2 + 51;
				car[i] = new Car(0, spawnX, spawnY + ((i % 4)) * 60, frameX,
						frameY);
			} else if (i / 4 == 1) {
				spawnX = frameX / 2 - 60;
				spawnY = frameY / 2 - 100;
				car[i] = new Car(1, spawnX, spawnY - ((i % 4)) * 60, frameX,
						frameY);
			} else if (i / 4 == 2) {
				spawnX = frameX / 2 - 90;
				spawnY = frameY / 2 + 20;
				car[i] = new Car(2, spawnX - ((i % 4)) * 60, spawnY, frameX,
						frameY);
			} else if (i / 4 == 3) {
				spawnX = frameX / 2 + 58;
				spawnY = frameY / 2 - 70;
				car[i] = new Car(3, spawnX + ((i % 4)) * 60, spawnY, frameX,
						frameY);
			}

		}
		light = true;

		KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					changeLight();
					yTimer = 0;
					light = !light;
				}
			}
		};
		addKeyListener(listener);
		setFocusable(true);
	}

	public void changeLight() {
		for (int i = 0; i < car.length; i++) {
			if (car[i].getDrive() && car[i].getState()) {
				car[i].setDrive(false);
			}
		}
	}

	public void space() {
		for (int i = 0; i < car.length; i++) {
			if (car[i].getDirection() == 0) { // cars moving up
				for (int k = 3; k >= 0; k--) {
					if (k == i)
						continue;
					if (car[k].getY() == car[i].getY() - 50) {
						car[i].setVel(0);
						break;
					} else
						car[i].setVel(1);
				}
			} else if (car[i].getDirection() == 1) { // cars moving down
				for (int k = 7; k >= 4; k--) {
					if (k == i)
						continue;
					if (car[k].getY() == car[i].getY() + 50) {
						car[i].setVel(0);
						break;
					} else
						car[i].setVel(1);
				}
			} else if (car[i].getDirection() == 2) { // cars moving right
				for (int k = 11; k >= 8; k--) {
					if (k == i)
						continue;
					if (car[k].getX() == car[i].getX() + 50) {
						car[i].setVel(0);
						break;
					} else
						car[i].setVel(1);
				}
			} else if (car[i].getDirection() == 3) { // cars moving left
				for (int k = 15; k >= 12; k--) {
					if (k == i)
						continue;
					if (car[k].getX() == car[i].getX() - 50) {
						car[i].setVel(0);
						break;
					} else
						car[i].setVel(1);
				}
			}
		}
	}

	public void update() {
		space();
		for (int i = 0; i < car.length; i++)
			car[i].update(frameX, frameY);
		for (int i = 0; i < car.length; i++) {
			if (car[i].getDrive() && car[i].getState()) {
				car[i].setDrive(false);
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.green);
		g2d.fillRect(0, 0, frameX, frameY);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(frameX / 2 - 70, 0, 130, frameY);
		g2d.fillRect(0, frameY / 2 - 80, frameX, 130);

		g2d.setColor(Color.WHITE);
		g2d.fillRect(frameX / 2 - 10, 0, 10, 25); // vertical dashes
		g2d.fillRect(frameX / 2 - 10, 45, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 90, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 135, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 180, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 360, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 405, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 450, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 495, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 540, 10, 25);
		g2d.fillRect(frameX / 2 - 10, 585, 10, 25);

		g2d.fillRect(-5, frameY / 2 - 20, 25, 10); // horizontal dashes
		g2d.fillRect(40, frameY / 2 - 20, 25, 10);
		g2d.fillRect(85, frameY / 2 - 20, 25, 10);
		g2d.fillRect(130, frameY / 2 - 20, 25, 10);
		g2d.fillRect(175, frameY / 2 - 20, 25, 10);
		g2d.fillRect(220, frameY / 2 - 20, 25, 10);
		g2d.fillRect(382, frameY / 2 - 20, 25, 10);
		g2d.fillRect(427, frameY / 2 - 20, 25, 10);
		g2d.fillRect(472, frameY / 2 - 20, 25, 10);
		g2d.fillRect(517, frameY / 2 - 20, 25, 10);
		g2d.fillRect(562, frameY / 2 - 20, 25, 10);
		g2d.fillRect(607, frameY / 2 - 20, 25, 10);

		for (int i = 0; i < car.length; i++)
			car[i].draw(g2d);

		g2d.setColor(Color.GREEN);
		g2d.fillOval(frameX / 2 - 15, frameY / 2 - 40, 20, 20); // vertical
																// lights
		g2d.fillOval(frameX / 2 - 15, frameY / 2 - 5, 20, 20);
		g2d.fillOval(frameX / 2 - 35, frameY / 2 - 23, 20, 20); // horizontal
																// lights
		g2d.fillOval(frameX / 2 + 4, frameY / 2 - 23, 20, 20);

		if (light) {
			yTimer++;
			g2d.setColor(Color.GREEN);
			g2d.fillOval(frameX / 2 - 15, frameY / 2 - 40, 20, 20); // vertical
																	// lights
			g2d.fillOval(frameX / 2 - 15, frameY / 2 - 5, 20, 20);
			g2d.setColor(Color.RED);
			g2d.fillOval(frameX / 2 - 35, frameY / 2 - 23, 20, 20); // horizontal
																	// lights
			g2d.fillOval(frameX / 2 + 4, frameY / 2 - 23, 20, 20);
			if (yTimer >= timerConstant) {
				for (int i = 0; i < car.length; i++) {
					if (car[i].getDirection() < 2 && !car[i].getDrive()) {
						car[i].setDrive(true);
					}
				}
			}
		} else {
			yTimer++;
			g2d.setColor(Color.RED);
			g2d.fillOval(frameX / 2 - 15, frameY / 2 - 40, 20, 20); // vertical
																	// lights
			g2d.fillOval(frameX / 2 - 15, frameY / 2 - 5, 20, 20);
			g2d.setColor(Color.GREEN);
			g2d.fillOval(frameX / 2 - 35, frameY / 2 - 23, 20, 20); // horizontal
																	// lights
			g2d.fillOval(frameX / 2 + 4, frameY / 2 - 23, 20, 20);
			if (yTimer >= timerConstant) {
				for (int i = 0; i < car.length; i++) {
					if (car[i].getDirection() >= 2 && !car[i].getDrive()) {
						car[i].setDrive(true);
					}
				}
			}
		}
	}

	public static void main(String args[]) throws InterruptedException {
		JFrame frame = new JFrame("Traffic Light Simulator 2015");
		Intersection program = new Intersection();
		frame.add(program);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameX, frameY);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);

		while (true) {
			program.update();
			program.repaint();
			Thread.sleep(5);
		}
	}
}
