package Model;

import Controller.StayPut;
import javafx.geometry.Point2D;

public class SpaceDebris extends SpaceObject {
	private int counter = 0;

	private boolean isHit = false;

	public SpaceDebris(Point2D location, int direction, float speed) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		setImplement(new StayPut());
		this.icon = "spaceDebris.png";
		this.size = 50;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		// do nothing
		return getImplement().interact(this, targetObj);
	}

	@Override
	public void move() {
		if (!this.isHit()) {
			double maxX = 768;
			double maxY = 768;
			// calculate delta between old coordinates and new ones based on speed and
			// direction
			double deltaX = this.speed * Math.sin(Math.toRadians(this.direction));
			double deltaY = this.speed * Math.cos(Math.toRadians(this.direction));
			double newX = this.getLocation().getX() + deltaX;
			double newY = this.getLocation().getY() + deltaY;

			// calculate position in case the boarder of the game board has been reached
			/*
			 * if (newX < 0) { newX = -newX; this.direction = MAX_ANGLE - this.direction; }
			 * else if (newX + this.getSize() > maxX) { newX = 2 * maxX - newX - 2 *
			 * this.getSize(); this.direction = MAX_ANGLE - this.direction; }
			 * 
			 * if (newY < 0) { newY = -newY; this.direction = MAX_ANGLE/2 - this.direction;
			 * if (this.direction < 0) { this.direction = MAX_ANGLE + this.direction; } }
			 * else if (newY + this.getSize() > maxY) { newY = 2 * maxY - newY - 2 *
			 * this.getSize(); this.direction = MAX_ANGLE/2 - this.direction; if
			 * (this.direction < 0) { this.direction = MAX_ANGLE + this.direction; } }
			 */
			this.direction += this.speed;
			// set coordinates
			this.location = new Point2D(newX, newY);
		}
	}

}
