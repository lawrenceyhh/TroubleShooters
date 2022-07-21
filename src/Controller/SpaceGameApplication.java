package Controller;

import Model.SpaceCraft;
import View.GameBoardUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SpaceGameApplication extends Application {

    private static final int GRID_LAYOUT_PADDING = 6;
    private static final int GRID_LAYOUT_PREF_HEIGHT = 768;
    private static final int GRID_LAYOUT_PREF_WIDTH = 768;

    private static final int GO_UPWARD = 0;
	private static final int GO_LEFTWARD = 90;
	private static final int GO_DOWNWARD = 180;
	private static final int GO_RIGHTWARD = 270;
    
    public static void startApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SpaceToolBar toolBar = new SpaceToolBar();
        GameBoardUI gameBoardUI = new GameBoardUI(toolBar);
        toolBar.initializeActions(gameBoardUI);

        Pane gridLayout = createLayout(gameBoardUI, toolBar);
        BackgroundImage myBI = new BackgroundImage(
                new Image("spaceBG.jpg", GRID_LAYOUT_PREF_HEIGHT, GRID_LAYOUT_PREF_WIDTH, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        gridLayout.setBackground(new Background(myBI));

        Scene scene = new Scene(gridLayout);
        KeyBoardAndMouseSteering steering = new KeyBoardAndMouseSteering(scene, gameBoardUI);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SpaceGame");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }

    private static Pane createLayout(GameBoardUI gameBoardUI, SpaceToolBar toolBar) {
        // GridPanes are divided into columns and rows, like a table
        GridPane gridLayout = new GridPane();
        gridLayout.setPrefSize(GRID_LAYOUT_PREF_WIDTH, GRID_LAYOUT_PREF_HEIGHT);
        gridLayout.setVgap(GRID_LAYOUT_PADDING);
        gridLayout.setPadding(new Insets(GRID_LAYOUT_PADDING));

        // add all components to the gridLayout
        // second parameter is column index, second parameter is row index of grid
        gridLayout.add(gameBoardUI, 0, 1);
        gridLayout.add(toolBar, 0, 0);
        return gridLayout;
    }

}
