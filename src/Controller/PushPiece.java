package Controller;

import Model.SmallPiece;
import Model.SpaceObject;

public class PushPiece implements ObjectInteraction {

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		if (targetObj instanceof SmallPiece) {
			SmallPiece target = (SmallPiece) targetObj;
			if (target.isBeingPushed()) {
				targetObj.setDirection(thisObj.getDirection());
				targetObj.setSpeed(thisObj.getSpeed());
				return true;
			}
		}
		return false;
	}

}
