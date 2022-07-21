package Controller;

import java.util.Optional;

import View.GameBoardUI;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;

public class SpaceToolBar extends ToolBar {
	private final Button start;
	private final Button stop;

	public SpaceToolBar() {
		this.start = new Button("Start");
		this.stop = new Button("Stop");
		updateToolBarStatus(false);
		getItems().addAll(this.start, new Separator(), this.stop);
	}

	public void initializeActions(GameBoardUI gameBoardUI) {
		this.start.setFocusTraversable(false);
		this.stop.setFocusTraversable(false);
		this.start.setOnAction(event -> gameBoardUI.startGame());

		this.stop.setOnAction(event -> {
			// stop the game while the alert is shown
			gameBoardUI.stopGame();

			Alert alert = new Alert(AlertType.CONFIRMATION, "Do you really want to stop the game?", ButtonType.YES,
					ButtonType.NO);
			alert.setTitle("Stop Game Confirmation");
			// By default the header additionally shows the Alert Type (Confirmation)
			// but we want to disable this to only show the question
			alert.setHeaderText("");

			Optional<ButtonType> result = alert.showAndWait();
			// reference equality check is OK here because the result will return the same
			// instance of the ButtonType
			if (result.isPresent() && result.get() == ButtonType.YES) {
				// reset the game board to prepare the new game
				gameBoardUI.setup();
			} else {
				// continue running
				gameBoardUI.startGame();
			}
		});
	}

	public void updateToolBarStatus(boolean running) {
		this.start.setDisable(running);
		this.stop.setDisable(!running);
	}

}
