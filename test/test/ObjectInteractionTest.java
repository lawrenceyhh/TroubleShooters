package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import Controller.BulletHit;
import Controller.GravityPull;
import Controller.ObjectInteraction;
import Controller.PushPiece;
import Model.Bullet;
import Model.SmallPiece;
import Model.SpaceCraft;
import Model.SpaceDebris;
import Model.SpaceObject;
import Model.StationaryPlanet;
import javafx.geometry.Point2D;

@ExtendWith(EasyMockExtension.class)
public class ObjectInteractionTest {

	private static int START_X_COORDINATE_1 = 0;
	private static int START_Y_COORDINATE_1 = 90;
	private static int START_X_COORDINATE_2 = 0;
	private static int START_Y_COORDINATE_2 = 0;

	@TestSubject
	private SpaceObject spaceCraft = new SpaceCraft();

	@TestSubject
	private StationaryPlanet stationaryPlanet = new StationaryPlanet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 0, 0);

	@TestSubject
	private SmallPiece smallPiece = new SmallPiece(new Point2D(START_X_COORDINATE_1,START_Y_COORDINATE_1), 0, 0);
	
	@TestSubject
	private Bullet bullet = new Bullet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 0, 0);
	
	@TestSubject
	private SpaceDebris debris = new SpaceDebris(new Point2D(START_X_COORDINATE_1,START_Y_COORDINATE_1), 0, 0);
	
	private ObjectInteraction interaction;
	
	@Mock
	private ObjectInteraction testingBulletHit;

	@Test
	public void testGravityPullDestroy() {
		stationaryPlanet = new StationaryPlanet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 0, 0);
		stationaryPlanet.setGravityField(50);
		smallPiece.setCrashed(true);
		interaction = new GravityPull();
		assertTrue(interaction.interact(stationaryPlanet, smallPiece));
	}
	
	@Test
	public void testGravityPullNotDestroyed() {
		stationaryPlanet = new StationaryPlanet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 0, 0);
		stationaryPlanet.setGravityField(50);
		smallPiece.setCrashed(false);
		interaction = new GravityPull();
		assertFalse(interaction.interact(stationaryPlanet, smallPiece));
	}
	
	@Test
	public void testGravityPullDirection() {
		smallPiece = new SmallPiece(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 50, 30);
		stationaryPlanet = new StationaryPlanet(new Point2D(START_X_COORDINATE_2, START_Y_COORDINATE_2), 0, 0);
		stationaryPlanet.setGravityField(150);
		interaction = new GravityPull();
		interaction.interact(stationaryPlanet, smallPiece);
		assertEquals(180, smallPiece.getDirection());
	}
	
	@Test
	public void testPushPieceDirection() {
		spaceCraft = new SpaceCraft();
		smallPiece = new SmallPiece(spaceCraft.getLocation(), 50, 30);
		smallPiece.setBeingPushed(true);
		interaction = new PushPiece();
		interaction.interact(spaceCraft, smallPiece);
		assertEquals(spaceCraft.getDirection(), smallPiece.getDirection());
	}
	
	@Test
	public void testPushPieceSameDirection() {
		spaceCraft = new SpaceCraft();
		int direction = 50;
		smallPiece = new SmallPiece(spaceCraft.getLocation(), direction, 30);
		smallPiece.setBeingPushed(false);
		interaction = new PushPiece();
		interaction.interact(spaceCraft, smallPiece);
		assertEquals(direction, smallPiece.getDirection());
	}
	
	@Test
	public void testPushPieceSpeed() {
		spaceCraft = new SpaceCraft();
		smallPiece = new SmallPiece(spaceCraft.getLocation(), 50, 30);
		smallPiece.setBeingPushed(true);
		interaction = new PushPiece();
		interaction.interact(spaceCraft, smallPiece);
		assertEquals(spaceCraft.getSpeed(), smallPiece.getSpeed());
	}

	@Test
	public void testBulletHitDistroy() {
		bullet = new Bullet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 50, 30);
		debris.setHit(true);
		interaction = new BulletHit();
		interaction.interact(bullet, debris);
		assertTrue(debris.isDestroyed());
	}
	
	@Test
	public void testBulletHitMiss() {
		bullet = new Bullet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 50, 30);
		debris.setHit(false);
		interaction = new BulletHit();
		interaction.interact(bullet, debris);
		assertFalse(debris.isDestroyed());
	}
	
	@Test
	public void testBulletHitInteractionReturn() {
		bullet = new Bullet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 50, 30);
		bullet.setImplement(testingBulletHit);
		debris.setHit(true);		
		EasyMock.expect(testingBulletHit.interact(bullet, debris)).andReturn(true);
		EasyMock.replay(testingBulletHit);	
		assertTrue(bullet.interact(bullet, debris));
	}
	
	@Test
	public void testStayPut() {
		this.stationaryPlanet = new StationaryPlanet(new Point2D(START_X_COORDINATE_1, START_Y_COORDINATE_1), 40, 90);
		this.smallPiece = new SmallPiece(new Point2D(START_X_COORDINATE_2, START_Y_COORDINATE_2), 0, 0);
		int size = this.stationaryPlanet.getSize();
		Point2D location = this.stationaryPlanet.getLocation();
		int direction = this.stationaryPlanet.getDirection();
		float speed = this.stationaryPlanet.getSpeed();
		String icon = this.stationaryPlanet.getImageLocation();
		boolean isDestroyed = this.stationaryPlanet.isDestroyed();
		
		stationaryPlanet.interact(stationaryPlanet, smallPiece);
		
		assertEquals(size, this.stationaryPlanet.getSize());
		assertEquals(location, this.stationaryPlanet.getLocation());
		assertEquals(direction, this.stationaryPlanet.getDirection());
		assertEquals(speed, this.stationaryPlanet.getSpeed());
		assertEquals(icon, this.stationaryPlanet.getImageLocation());
		assertEquals(isDestroyed, this.stationaryPlanet.isDestroyed());
	}
	
}
