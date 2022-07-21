package Controller;

import Model.SpaceDebris;
import Model.SpaceObject;

public class BulletHit implements ObjectInteraction {

	@Override
	public boolean interact(SpaceObject thisObj, SpaceObject targetObj) {
		if (targetObj instanceof SpaceDebris) {
			SpaceDebris target = (SpaceDebris) targetObj;
			if (target.isHit()) {
				targetObj.setDestroyed(true);
				return true;
			}
		}
		return false;
	}

}
