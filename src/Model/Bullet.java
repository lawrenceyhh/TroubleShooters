package Model;

import Controller.BulletHit;
import javafx.geometry.Point2D;

public class Bullet extends SpaceObject {

	public Bullet(Point2D location, int direction, float speed) {
		this.location = location;
		this.direction = direction;
		this.speed = speed;
		setImplement(new BulletHit());
		this.icon = "bullet.png";
		this.size = 20;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		double maxX = 768;
		double maxY = 768;
		// calculate delta between old coordinates and new ones based on speed and
		// direction
		double deltaX = this.speed * Math.sin(Math.toRadians(this.direction));
		double deltaY = this.speed * Math.cos(Math.toRadians(this.direction));
		double newX = this.getLocation().getX() + deltaX;
		double newY = this.getLocation().getY() + deltaY;

		if (newX > maxX || newY > maxY || newX < 0 || newY < 0) {
			this.setDestroyed(true);
		} else {
			this.location = new Point2D(newX, newY);
		}
	}

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		return getImplement().interact(this, targetObj);

	}

}
