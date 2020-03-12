package ps.gamedev.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public class HexPolygon
        extends Polygon {
    private final HexField hexField;
    static final double alpha = 1.0471975512;  //PI/3.0
    static final Color BASE_FILL = Color.YELLOW, BASE_STROKE = Color.BLACK;
    static final Color SELECTED_FILL = Color.GREEN, SELECTED_STROKE = Color.RED, SELECTED_NEIGHBOUR = Color.LIMEGREEN;

    public HexPolygon(HexField hexField) {
        super();
        this.hexField = hexField;
        this.hexField.setParent(this);

        double r = 0.8 * this.hexField.getHexHeight() / 2.0;
        double dAngle = Math.toRadians(30);


        for (int i = 0; i < 6; i++) {
            double angle = alpha * i + dAngle;
            this.getPoints().addAll(this.hexField.getCenterX() + r * Math.cos(angle), this.hexField.getCenterY() + r * Math.sin(angle));
        }

        this.setFill(BASE_FILL);
        this.setStroke(BASE_STROKE);
        this.setStrokeWidth(1.0);
        this.hexField.setSelected(false);

        initHandlers();
    }

    public void initHandlers() {
        this.setOnMouseEntered(e -> {
            this.setStroke(SELECTED_STROKE);
            this.toFront();
            setNeighboursColor(SELECTED_NEIGHBOUR);
        });
        this.setOnMouseExited(e ->
        {
            this.setStroke(BASE_STROKE);
            setNeighboursColor(BASE_FILL);
        });

        this.setOnMouseClicked(e ->
                flipSelected());
    }

    private void setNeighboursColor(Color fill) {
        if (this.hexField.getNeighbours() == null)
            return;
        this.hexField.getNeighbours().forEach(e ->
                e.getParent().setFill(fill == SELECTED_NEIGHBOUR ? fill : (e.isSelected() ? SELECTED_FILL : fill))
        );
    }

//    public List<HexPolygon> getNeighbours() {
//        return this.neighbours;
//    }

    private void setSelected(boolean selected) {
        this.hexField.setSelected(selected);
        this.setFill(selected ? SELECTED_FILL : BASE_FILL);
    }

    private boolean isSelected() {
        return this.hexField.isSelected();
    }

    private void flipSelected() {
        setSelected(!isSelected());
    }

    @Override
    public String toString() {
        return this.hexField.toString();
    }
}
