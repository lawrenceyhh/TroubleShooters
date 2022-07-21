package Model;

import Controller.GravityPull;
import javafx.geometry.Point2D;

public class StationaryPlanet extends SpaceObject {

	public StationaryPlanet(Point2D location, int direction, float speed) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		setImplement(new GravityPull());
		this.icon = "stationaryPlanet.png";
		this.size = 60;
		this.gravityField = 100;
	}

	private double gravityField;

	public double getGravityField() {
		return gravityField;
	}

	public void setGravityField(double gravityField) {
		this.gravityField = gravityField;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		return getImplement().interact(this, targetObj);

	}

}
