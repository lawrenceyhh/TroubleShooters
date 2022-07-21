package Controller;

import Model.Bullet;
import Model.SpaceCraft;
import View.GameBoardUI;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class KeyBoardAndMouseSteering {
	private static final int GO_UPWARD = 180;
	private static final int GO_LEFTWARD = 270;
	private static final int GO_DOWNWARD = 0;
	private static final int GO_RIGHTWARD = 90;

	private final SpaceCraft userSpaceCraft;

	public KeyBoardAndMouseSteering(Scene scene, GameBoardUI gameBoardUI) {
		this.userSpaceCraft = gameBoardUI.gameBoard.getPlayer().getSpaceCraft();
		
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				double nowX = userSpaceCraft.getLocation().getX();
				double nowY = userSpaceCraft.getLocation().getY();

				switch (event.getCode()) {
				case UP:
					//userSpaceCraft.setDirection(GO_UPWARD);
					//System.out.println("GO UP");
					//userSpaceCraft.setLocation(new Point2D(nowX, nowY + 5));
					userSpaceCraft.goingUP = true;
					userSpaceCraft.goingDOWN = false;
					userSpaceCraft.goingLEFT = false;
					userSpaceCraft.goingRIGHT = false;
					break;
				case DOWN:
					//userSpaceCraft.setDirection(GO_DOWNWARD);
					//System.out.println("GO DOWN");
					//userSpaceCraft.setLocation(new Point2D(nowX, nowY - 5));
					userSpaceCraft.goingDOWN = true;
					userSpaceCraft.goingUP = false;
					userSpaceCraft.goingLEFT = false;
					userSpaceCraft.goingRIGHT = false;
					break;
				case LEFT:
					//userSpaceCraft.setDirection(GO_LEFTWARD);
					//System.out.println("GO LEFT");
					//userSpaceCraft.setLocation(new Point2D(nowX - 5, nowY));
					userSpaceCraft.goingLEFT = true;
					userSpaceCraft.goingDOWN = false;
					userSpaceCraft.goingUP = false;
					userSpaceCraft.goingRIGHT = false;
					break;
				case RIGHT:
					//userSpaceCraft.setDirection(GO_RIGHTWARD);
					//System.out.println("GO RIGHT");
					//userSpaceCraft.setLocation(new Point2D(nowX + 5, nowY));
					userSpaceCraft.goingRIGHT = true;
					userSpaceCraft.goingDOWN = false;
					userSpaceCraft.goingLEFT = false;
					userSpaceCraft.goingUP = false;
					break;
				default:
					return;
				}
				//userSpaceCraft.move();
			}
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				double nowX = userSpaceCraft.getLocation().getX();
				double nowY = userSpaceCraft.getLocation().getY();

				switch (event.getCode()) {
				case UP:
					//userSpaceCraft.setDirection(GO_UPWARD);
					//System.out.println("GO UP");
					//userSpaceCraft.setLocation(new Point2D(nowX, nowY + 5));
					userSpaceCraft.goingUP = false;
					break;
				case DOWN:
					//userSpaceCraft.setDirection(GO_DOWNWARD);
					//System.out.println("GO DOWN");
					//userSpaceCraft.setLocation(new Point2D(nowX, nowY - 5));
					userSpaceCraft.goingDOWN = false;
					break;
				case LEFT:
					//userSpaceCraft.setDirection(GO_LEFTWARD);
					//System.out.println("GO LEFT");
					//userSpaceCraft.setLocation(new Point2D(nowX - 5, nowY));
					userSpaceCraft.goingLEFT = false;
					break;
				case RIGHT:
					//userSpaceCraft.setDirection(GO_RIGHTWARD);
					//System.out.println("GO RIGHT");
					//userSpaceCraft.setLocation(new Point2D(nowX + 5, nowY));
					userSpaceCraft.goingRIGHT = false;
					break;
				default:
					return;
				}
				//userSpaceCraft.move();
			}
        });
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	Point2D carPosition = userSpaceCraft.getLocation();
        		Point2D clickPosition = new Point2D(event.getX(), event.getY());
        		double deltaX = clickPosition.getX() - carPosition.getX();
        		deltaX = Math.abs(deltaX);
        		double deltaY = clickPosition.getY() - carPosition.getY();
        		int degree = (int) Math.toDegrees(Math.atan2(deltaY, deltaX));

        		if (clickPosition.getX() > carPosition.getX()) {
        			degree = 90 - degree;
        		} else {
        			degree = 270 + degree;
        		}

        		Bullet bullet = new Bullet(userSpaceCraft.getLocation(), degree, 10);
        		gameBoardUI.gameBoard.getSpaceObjects().add(bullet);
			}
        });
		
	}
	 

}
