import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Car {

	private int x, y, direction, vel = 1; // directions: 0=up, 1=down, 2=right, 3=left
	private boolean drive, state; // state tells you if car is before or after line, drive tells you if car is to move or to come to stop
	private final static int width = 20, height = 20;
	private int frameX, frameY;

	public Car(int direction, int spawnX, int spawnY, int frameX, int frameY) {
		this.direction = direction;
		this.x = spawnX;
		this.y = spawnY;
		this.frameX = frameX;
		this.frameY = frameY;
		if (direction <= 1)
			drive = true;
		else if (direction >= 1)
			drive = false;
	}

	public void generate() {
		switch (direction) {
		case 0:
			x = frameX / 2 + 30;
			y = frameY - height;
			break;
		case 1:
			x = frameX / 2 - 60;
			y = 0;
			break;
		case 2:
			x = 0;
			y = frameY / 2 + 20;
			break;
		case 3:
			x = frameX;
			y = frameY / 2 - 70;
			break;
		}
	}

	public void update(int frameX, int frameY) {
		this.frameX = frameX;
		this.frameY = frameY;
		if (x >= frameX || x <= 0)
			generate();
		if (y >= frameY || y <= 0)
			generate();

		if (drive) {

			switch (direction) {
			case 0:
				y -= vel;
				if (y < frameY / 2 + 50 && y > -height)
					state = false;
				else
					state = true;
				break;
			case 1:
				y += vel;
				if (y > frameY / 2 - 100 && y < frameY)
					state = false;
				else
					state = true;
				break;
			case 2:
				x += vel;
				if (x > frameX / 2 - 90 && x < frameX)
					state = false;
				else
					state = true;
				break;
			case 3:
				x -= vel;
				if (x < frameX / 2 + 58 && x > -width)
					state = false;
				else
					state = true;
				break;
			}
		} else if (!drive) {
			switch (direction) {
			case 0:
				if (y > frameY / 2 + 50)
					y -= vel;
				break;
			case 1:
				if (y < frameY / 2 - 100)
					y += vel;
				break;
			case 2:
				if (x < frameX / 2 - 90)
					x += vel;
				break;
			case 3:
				if (x > frameX / 2 + 58)
					x -= vel;
				break;
			}
		}
	}

	public void draw(Graphics2D g2d) {
		ImageIcon icon = new ImageIcon("CarUp.png");
		if (direction == 0)
			icon = new ImageIcon("E:/Desktop/res/CarUp.png");
		else if (direction == 1)
			icon = new ImageIcon("E:/Desktop/res/CarDown.png");
		else if (direction == 2)
			icon = new ImageIcon("E:/Desktop/res/CarRight.png");
		else if (direction == 3)
			icon = new ImageIcon("E:/Desktop/res/CarLeft.png");

		Image img = icon.getImage();
		g2d.drawImage(img, x, y, null);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirection() {
		return direction;
	}

	public int getVel() {
		return vel;
	}

	public boolean getDrive() {
		return drive;
	}

	public boolean getState() {
		return state;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	public void setDrive(boolean drive) {
		this.drive = drive;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
