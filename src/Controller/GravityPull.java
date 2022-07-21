package Controller;

import java.awt.geom.Point2D;

import Model.SmallPiece;
import Model.SpaceObject;
import Model.StationaryPlanet;

public class GravityPull implements ObjectInteraction {
	private int ANGLE_90_DEGREES = 90;
	private int ANGLE_270_DEGREES = 270;

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		if (targetObj instanceof SmallPiece) {
			StationaryPlanet thisPlanet = (StationaryPlanet) thisObj;
			double thisX = thisObj.getLocation().getX();
			double thisY = thisObj.getLocation().getY();
			double targetX = targetObj.getLocation().getX();
			double targetY = targetObj.getLocation().getY();
			double distance = Math.abs(Point2D.distance(thisX, thisY, targetX, targetY));

			// this method is referred from Bumpers exercise
			if (distance < thisPlanet.getGravityField()) {
				SmallPiece target = (SmallPiece) targetObj;
				double deltaX = thisX - targetX;
				deltaX = Math.abs(deltaX);
				double deltaY = thisY - targetY;
				int degree = (int) Math.toDegrees(Math.atan2(deltaY, deltaX));

				if (thisX > targetX) {
					degree = ANGLE_90_DEGREES - degree;
				} else {
					degree = ANGLE_270_DEGREES + degree;
				}
				targetObj.setDirection(degree);
				// if smallPiece crashed into the Planet, it is destroyed
				if (target.isCrashed()) {
					target.setDestroyed(true);
					return true;
				}
			}
		}
		return false;
	}

}
