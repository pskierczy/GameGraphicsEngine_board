package ps.gamedev.Board;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicsEngine
        extends Pane {
    Rectangle background;


    public GraphicsEngine(double width, double height) {
        super();
        this.setTranslateX(width / 2);
        this.setTranslateY(height / 2);
        background = new Rectangle(-width / 2, -height / 2, width, height);
        background.setFill(Color.WHITE);
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(1.0);
        this.getChildren().add(background);
    }

    public void draw(GraphicsContext graphicsContext) {

    }

}
