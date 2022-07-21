package View;

import java.net.URL;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Point2D;

import Controller.GameBoard;
import Controller.KeyBoardAndMouseSteering;
import Controller.SpaceToolBar;
import Model.SpaceObject;
import Model.GameOutcome;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameBoardUI extends Canvas {

    public GameBoard gameBoard;
    private SpaceToolBar spaceToolBar;
    private Timer gameTimer;
    private HashMap<String, Image> imageCache;

    private static final Color SPACE = Color.BLACK;

    private static final int UPDATE_PERIOD = 1000 / 25;
    private static final int DEFAULT_WIDTH = 768;
    private static final int DEFAULT_HEIGHT = 768;
    private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public static Dimension2D getPreferredSize() {
        return DEFAULT_SIZE;
    }

    public void startGame() {
    	
        if (!this.gameBoard.isRunning()) {
            this.gameBoard.startGame();
            this.spaceToolBar.updateToolBarStatus(true);
            draw();
        }
    }

    public void stopGame() {
        if (this.gameBoard.isRunning()) {
            this.gameBoard.stopGame();
            this.spaceToolBar.updateToolBarStatus(false);
            this.gameTimer.cancel();
        }
    }

    public void playMusic() {

    }

    public GameBoardUI(SpaceToolBar gameToolBar) {
        this.spaceToolBar = gameToolBar;
        setup();
    }

    public void setup() {
        setupGameBoard();
        setupImageCache();
        this.spaceToolBar.updateToolBarStatus(false);
        startTimer();
    }

    private void setupGameBoard() {
        Dimension2D size = getPreferredSize();
        this.gameBoard = new GameBoard(size);
        // this.gameBoard.setAudioPlayer(new AudioPlayer());
        widthProperty().set(size.getWidth());
        heightProperty().set(size.getHeight());
    }

    private void startTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateGame();
            }
        };
        if (this.gameTimer != null) {
            this.gameTimer.cancel();
        }
        this.gameTimer = new Timer();
        this.gameTimer.scheduleAtFixedRate(timerTask, UPDATE_PERIOD, UPDATE_PERIOD);
    }

    public void stopMusic() {

    }

    private void updateGame() {
        if (gameBoard.isRunning()) {
            // updates car positions and re-renders graphics
            this.gameBoard.updateMap();
            // when this.gameBoard.getOutcome() is OPEN, do nothing
            if (this.gameBoard.getGameOutcome() == GameOutcome.WON) {
				showAsyncAlert("Congratulations! You won!!");
				this.stopGame();
            }
            draw();
        }
    }
    
    //this method is referred from Bumpers
	private void showAsyncAlert(String message) {
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(message);
			alert.showAndWait();
			this.setup();
		});
	}

    private void setupImageCache() {
        this.imageCache = new HashMap<String, Image>();
        this.imageCache.putIfAbsent("bullet.png", this.getImage("bullet.png"));
        for (SpaceObject object : this.gameBoard.getSpaceObjects()) {
            String imageLocation = object.getImageLocation();
            this.imageCache.putIfAbsent(imageLocation, this.getImage(imageLocation));
        }
        // TODO here I've changed something
        String playerImageLocation = this.gameBoard.getPlayer().getSpaceCraft().getImageLocation();
        this.imageCache.put(playerImageLocation, getImage(playerImageLocation));
    }

    private Image getImage(String objectImageFilePath) {
    		URL objImageUrl = getClass().getClassLoader().getResource(objectImageFilePath);
    		if (objImageUrl == null) {
    			throw new IllegalArgumentException(
    					"Please ensure that your resources folder contains the appropriate files for this exercise.");
    		}
    		return new Image(objImageUrl.toExternalForm());
    }

    private void draw() {
        this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
        for (SpaceObject object : this.gameBoard.getSpaceObjects()) {
        	if (!object.isDestroyed()) {
        		drawObject(object);
        	}
        }
        //System.out.println("drawing stuff");
        drawObject(this.gameBoard.getPlayer().getSpaceCraft());
    }

    private void drawObject(SpaceObject spaceObject) {
        Point2D objectPosition = spaceObject.getLocation();
        getGraphicsContext2D().drawImage((Image) this.imageCache.get(spaceObject.getImageLocation()),
                objectPosition.getX(), objectPosition.getY(), spaceObject.getSize(), spaceObject.getSize());
    }
}
