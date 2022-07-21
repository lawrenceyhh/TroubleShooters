package Model;

import Controller.ObjectInteraction;
import javafx.geometry.Point2D;

public abstract class SpaceObject implements ObjectInteraction {

	protected static final int MAX_ANGLE = 360;

	protected int size;
	protected Point2D location;
	protected int direction;
	protected float speed;
	protected String icon;
	protected boolean isDestroyed;
	protected ObjectInteraction implement;

	public abstract void move();

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public String getImageLocation() {
		return icon;
	}

	public void setImageLocation(String icon) {
		this.icon = icon;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public ObjectInteraction getImplement() {
		return implement;
	}

	public void setImplement(ObjectInteraction implement) {
		this.implement = implement;
	}

}
