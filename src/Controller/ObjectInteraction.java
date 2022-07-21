package Controller;

import Model.SpaceObject;

public interface ObjectInteraction {

	public abstract boolean interact(SpaceObject thisObj, SpaceObject targetObj);

}
