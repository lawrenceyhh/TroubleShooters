package Model;

import Controller.StayPut;
import javafx.geometry.Point2D;

public class Moon extends SpaceObject {

	public Moon(Point2D location, int direction, float speed) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		setImplement(new StayPut());
		this.icon = "moon.png";
		this.size = 50;
	}

	@Override
	public void move() {
		// run around StationaryPlanet in circle 
		double deltaX = this.speed * Math.sin(Math.toRadians(this.direction));
		double deltaY = this.speed * Math.cos(Math.toRadians(this.direction));
		double newX = this.getLocation().getX() + deltaX;
		double newY = this.getLocation().getY() + deltaY;
		this.direction += this.speed;
		this.location = new Point2D(newX, newY);
	}

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		return getImplement().interact(this, targetObj);
		
	}

}
