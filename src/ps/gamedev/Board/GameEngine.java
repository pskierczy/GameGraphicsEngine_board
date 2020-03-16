package ps.gamedev.Board;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameEngine
        extends Stage {
    GraphicsEngine graphicsEngine;
    HexBoard hexBoard;
    Group root;
    AnchorPane anchorPane;

    public GameEngine(double width, double height) {
        super();
        anchorPane = new AnchorPane();
        //root = new Group();
        this.setTitle("Hex board game");
        this.setScene(new Scene(anchorPane, width, height));
        this.getScene().setFill(Color.LIGHTGRAY);

        initHexBoard(0, 0, 20, 30, 25, true);
        initGraphics(width * 0.8, height * 0.9, width * 0.2, height * 0.1);

    }

    private void initGraphics(double width, double height, double layoutX, double layoutY) {
        this.graphicsEngine = new GraphicsEngine(width, height);
        this.graphicsEngine.setLayoutX(layoutX);
        this.graphicsEngine.setLayoutY(layoutY);
        this.graphicsEngine.initHexPolys(this.hexBoard);
        this.anchorPane.getChildren().add(this.graphicsEngine);
    }

    private void initHexBoard(double centerX, double centerY, double hexSize, int rows, int cols, boolean isoView) {
        hexBoard = new HexBoard(centerX, centerY, hexSize, rows, cols);
        //this.graphicsEngine.getChildren().add(hexBoard);
    }

    public GraphicsEngine getGraphicsEngine() {
        return graphicsEngine;
    }

    public void setGraphicsEngine(GraphicsEngine graphicsEngine) {
        this.graphicsEngine = graphicsEngine;
    }

    public HexBoard getHexBoard() {
        return hexBoard;
    }

    public void setHexBoard(HexBoard hexBoard) {
        this.hexBoard = hexBoard;
    }
}
