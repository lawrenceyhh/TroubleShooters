package Model;

import Controller.StayPut;
import javafx.geometry.Point2D;

public class SmallPiece extends SpaceObject {
	private boolean beingPushed = false; // collide w/ SpaceCraft
	private boolean isCrashed = false; // collide w/ StationaryPlanet

	public SmallPiece(Point2D location, int direction, float speed) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		setImplement(new StayPut());
		this.icon = "smallPiece.png";
		this.size = 50;
	}

	// this method is referred from Bumpers
	@Override
	public void move() {
		double maxX = 768;
		double maxY = 768;

		double deltaX = this.speed * Math.sin(Math.toRadians(this.direction));
		double deltaY = this.speed * Math.cos(Math.toRadians(this.direction));
		double newX = this.getLocation().getX() + deltaX;
		double newY = this.getLocation().getY() + deltaY;

		if (newX < 0) {
			newX = maxX - this.getSize();
		} else if (newX + this.getSize() > maxX) {
			newX = 0;
		}

		if (newY < 0) {
			newY = maxY - this.getSize();
		} else if (newY + this.getSize() > maxY) {
			newY = 0;
		}

		this.location = new Point2D(newX, newY);

	}

	public boolean isBeingPushed() {
		return beingPushed;
	}

	public void setBeingPushed(boolean beingPushed) {
		this.beingPushed = beingPushed;
	}

	public boolean isCrashed() {
		return isCrashed;
	}

	public void setCrashed(boolean isCrashed) {
		this.isCrashed = isCrashed;
	}

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		return getImplement().interact(this, targetObj);
	}

}
