package Model;

import Controller.PushPiece;
import javafx.geometry.Point2D;

public class SpaceCraft extends SpaceObject {

	public boolean goingUP;
	public boolean goingDOWN;
	public boolean goingLEFT;
	public boolean goingRIGHT;
	
	public SpaceCraft() {
		this.speed = 5;
		this.setImplement(new PushPiece());
		this.icon = "UFO.png";
		this.size = 40;
	}

    @Override
    public void move() {
    	
    	if (goingUP) {
    		this.direction = 180;
    	} else if (goingDOWN) {
    		this.direction = 0;
    	} else if (goingLEFT) {
    		this.direction = 270;
    	} else if (goingRIGHT) {
    		this.direction = 90;
    	} else {
    		return;
    	}
    	
        double maxX = 768;
        double maxY = 768;

		double deltaX = this.getSpeed() * Math.sin(Math.toRadians(this.direction));
		double deltaY = this.getSpeed() * Math.cos(Math.toRadians(this.direction));
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

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		return getImplement().interact(this, targetObj);

	}

}
