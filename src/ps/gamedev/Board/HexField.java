package ps.gamedev.Board;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import sun.nio.cs.ext.MacHebrew;

import java.util.ArrayList;
import java.util.List;

public class HexField
        extends Polygon {
    private double centerX, centerY, size, h, r, w;
    private List<HexField> neighbours;
    static final double cos30 = 0.86602540378;  //cos(30)
    static final double sqrt3 = 1.73205080757;  //sqrt(3)
    static final double alpha = 1.0471975512;  //PI/3.0
    static final Color BASE_FILL = Color.YELLOW, BASE_STROKE = Color.BLACK;
    static final Color SELECTED_FILL = Color.GREEN, SELECTED_STROKE = Color.RED, SELECTED_NEIGHBOUR = Color.LIMEGREEN;
    private boolean selected;

    public static HexField FlatUnitary() {
        return new HexField(0, 0, 1);
    }

    public static HexField TipUnitary() {
        return new HexField(0, 0, 1, -30);
    }


    public HexField() {
        this(0, 0, 1, 0);
    }

    public HexField(double centerX, double centerY, double size) {
        this(centerX, centerY, size, 0.0);
    }

    public HexField(double centerX, double centerY, double size, double angleInDegrees) {
        super();
        this.neighbours = new ArrayList<>(6);
        this.centerX = centerX;
        this.centerY = centerY;
        this.size = size;
        this.w = size * sqrt3;
        this.h = size * 2;
        this.r = h / 2.0;


        double dAngle = Math.toRadians(angleInDegrees + 30);
        for (int i = 0; i < 6; i++) {
            double angle = alpha * i + dAngle;
            this.getPoints().addAll(this.centerX + r * Math.cos(angle), this.centerY + r * Math.sin(angle));
        }
        this.setFill(BASE_FILL);
        this.setStroke(BASE_STROKE);
        this.setStrokeWidth(1.0);
        this.selected = false;

        //initHandlers();
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
        if (neighbours == null)
            return;
        neighbours.forEach(e ->
                e.setFill(fill == SELECTED_NEIGHBOUR ? fill : (e.isSelected() ? SELECTED_FILL : fill))
        );
    }

    public List<HexField> getNeighbours() {
        //TODO:update
        return this.neighbours;
    }

    private void setSelected(boolean selected) {
        this.selected = selected;
        this.setFill(selected ? SELECTED_FILL : BASE_FILL);
    }

    private boolean isSelected() {
        return this.selected;
    }

    private void flipSelected() {
        setSelected(!isSelected());
    }

    public double getH() {
        return this.h;
    }

    public double getW() {
        return this.w;
    }

    @Override
    public String toString() {
        return String.format("CenterX=%8.3f, CenterY=%8.3f, size=%8.3f", this.centerX, this.centerY, this.size);
    }

    public void addNeighbour(HexField neighbour) {
        if (neighbour == null)
            return;
        this.neighbours.add(neighbour);
    }
}
