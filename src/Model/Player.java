package Model;

import javafx.geometry.Point2D;

public class Player {
    private SpaceCraft spaceCraft;
    private int START_X_COORDINATE = 0;
    private int START_Y_COORDINATE = 0;
    private int START_DIRECTION = 90;

    public Player(SpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
        this.spaceCraft.setLocation(new Point2D(START_X_COORDINATE, START_Y_COORDINATE));
        this.spaceCraft.setDirection(START_DIRECTION);
    }

    public SpaceCraft getSpaceCraft() {
        return spaceCraft;
    }

    public void setSpaceCraft(SpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
    }

}
