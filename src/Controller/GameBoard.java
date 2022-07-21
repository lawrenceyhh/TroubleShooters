package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class GameBoard {

	private Dimension2D size;
	private boolean running;

	private final Player player;

	private List<SpaceObject> spaceObjects = new ArrayList<SpaceObject>();
	private List<SmallPiece> smallPieces = new ArrayList<SmallPiece>();

	private GameOutcome gameOutcome = GameOutcome.NO_OUTCOME_YET;

	public GameBoard(Dimension2D size) {
		this.size = size;
		// SpaceDebris tester = new SpaceDebris(new Point2D(256, 256), 0, 3);
		SpaceCraft playerSpaceCraft = new SpaceCraft();
		this.player = new Player(playerSpaceCraft);
		System.out.println("image set");
		// spaceObjects.add(tester);
		SmallPiece pieceOne = new SmallPiece(new Point2D(200, 0), 0, 0);
		SmallPiece pieceTwo = new SmallPiece(new Point2D(500, 500), 0, 0);
		spaceObjects.add(playerSpaceCraft);
		spaceObjects.add(new SpaceDebris(new Point2D(200, 250), 0, 0));
		spaceObjects.add(new SpaceDebris(new Point2D(400, 450), 0, 0));
		spaceObjects.add(pieceOne);
		spaceObjects.add(pieceTwo);
		smallPieces.add(pieceOne);
		smallPieces.add(pieceTwo);
		spaceObjects.add(new Moon(new Point2D(200-50, 200), 0, 15));
		spaceObjects.add(new StationaryPlanet(new Point2D(200, 200), 0, 0));
	}

	public boolean isRunning() {
		return this.running;
	}

	public void startGame() {
		this.running = true;
	}

	public void stopGame() {
		this.running = false;
	}

	public void updateMap() {
		for (SpaceObject object : spaceObjects) {
			if (!object.isDestroyed()) {
				object.move();
			} /*else if (object instanceof SpaceDebris) {
					spaceObjects.add(new SmallPiece(object.getLocation(), 0, 1));
					spaceObjects.add(new SmallPiece(object.getLocation(), 120, 1));
					spaceObjects.add(new SmallPiece(object.getLocation(), 240, 1));
				}
				spaceObjects.remove(object);
			 	
			}*/
		}
		if (isWinner()) {
			gameOutcome = GameOutcome.WON;
		}
		// this.player.getSpaceCraft().move();
		// detect SmallPiece.beingPushed, SmallPiece.hitPlanet, SpaceDebris.isHit here
		updateObjectStatus();
		// loop each object.Interaction w/ other object
		updateObjectInteraction();
	}

	public List<SpaceObject> getSpaceObjects() {
		// TODO Auto-generated method stub
		return this.spaceObjects;
	}

	public Player getPlayer() {
		return this.player;
	}

	public GameOutcome getGameOutcome() {
		return gameOutcome;
	}

	public void setGameOutcome(GameOutcome gameOutcome) {
		this.gameOutcome = gameOutcome;
	}

	private boolean isWinner() {
		
		for (SmallPiece smallPiece : smallPieces) {
			if (smallPiece.isDestroyed() != true) {
				return false;
			}
		}
		return true;
	}

	// this method is referred from Bumpers
	public boolean haveCollided(SpaceObject objOne, SpaceObject objTwo) {
		Point2D one = objOne.getLocation();
		Point2D two = objTwo.getLocation();

		boolean above = one.getY() + objOne.getSize() < two.getY();
		boolean below = one.getY() > two.getY() + objTwo.getSize();
		boolean right = one.getX() + objOne.getSize() < two.getX();
		boolean left = one.getX() > two.getX() + objTwo.getSize();
		return (!above && !below && !right && !left);
	}

	public void updateObjectStatus() {
		for (SpaceObject object : spaceObjects) {
			if (object instanceof SmallPiece) {
				SmallPiece smallPiece = (SmallPiece) object;
				smallPiece.setBeingPushed(haveCollided(smallPiece, player.getSpaceCraft()));

				for (SpaceObject objectTwo : spaceObjects) {
					if (objectTwo instanceof StationaryPlanet) {
						StationaryPlanet planet = (StationaryPlanet) objectTwo;
						smallPiece.setCrashed(haveCollided(smallPiece, planet));
					}
				}
			} else if (object instanceof SpaceDebris) {
				SpaceDebris debris = (SpaceDebris) object;
				for (SpaceObject objectTwo : spaceObjects) {
					if (objectTwo instanceof Bullet) {
						Bullet bullet = (Bullet) objectTwo;
						debris.setHit(haveCollided(debris, bullet));
					}
				}
			}
		}
	}

	public void updateObjectInteraction() {
		for (int i = 0; i < spaceObjects.size() - 1; i++) {
			for (int j = i + 1; j < spaceObjects.size(); j++) {
				SpaceObject objOne = spaceObjects.get(i);
				SpaceObject objTwo = spaceObjects.get(j);
				objOne.interact(objOne, objTwo);
				objTwo.interact(objTwo, objOne);
			}
		}
	}

}
