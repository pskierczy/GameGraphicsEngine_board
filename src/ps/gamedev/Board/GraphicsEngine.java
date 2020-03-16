package ps.gamedev.Board;

import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class GraphicsEngine
        extends SubScene {
    private double width, height;
    private Group root;
    private Canvas background;
    private List<HexPolygon> hexObjects;

    public GraphicsEngine(double width, double height) {
        super(new Group(), width, height);
        root = (Group) this.getRoot();
        this.width = width;
        this.height = height;
        this.root.setTranslateX(width / 2.0);
        this.root.setTranslateY(height / 2.0);

        initBackground();
        this.root.getChildren().add(background);

        //drawBackground();
    }

    private void initBackground() {
        background = new Canvas(width, height);
        background.setLayoutX(-width / 2.0);
        background.setLayoutY(-height / 2.0);
//        background.setTranslateX(-width / 2.0);
//        background.setTranslateY(-height / 2.0);
        GraphicsContext graphicsContext = background.getGraphicsContext2D();
        graphicsContext.setFill(Color.YELLOW);
//        graphicsContext.fillRect(-width / 2.0, -height / 2.0, width, height);
        graphicsContext.fillRect(0, 0, width, height);
    }

    public void initHexPolys(HexBoard board) {
        this.hexObjects = board.getHexPolys();
        this.root.getChildren().addAll(hexObjects);
    }

    public void draw(GraphicsContext graphicsContext) {

    }

    public void drawBackground() {

    }

}
