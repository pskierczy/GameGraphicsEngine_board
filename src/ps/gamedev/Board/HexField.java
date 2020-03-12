package ps.gamedev.Board;

import java.util.ArrayList;
import java.util.List;

public class HexField {
    private double centerX, centerY, height, hexHeight, hexWidth;
    private double BASE_SIZE;
    private List<HexField> neighbours;
    static final private double sqrt3 = 1.73205080757;  //sqrt(3)
    private boolean selected;
    private HexPolygon parent;

    public static HexField FlatUnitary() {
        return new HexField(0, 0, 1);
    }

    public HexField() {
        this(0, 0, 1);
    }

    public HexField(double centerX, double centerY, double size) {
        this(centerX, centerY, size, 0.0);
    }

    public HexField(double centerX, double centerY, double size, double height) {
        this.neighbours = new ArrayList<>(6);
        this.centerX = centerX;
        this.centerY = centerY;
        this.BASE_SIZE = size;
        this.height = height;
        this.hexWidth = BASE_SIZE * sqrt3;
        this.hexHeight = BASE_SIZE * 2.0;
        this.selected = false;
    }

    public List<HexField> getNeighbours() {
        return this.neighbours;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void flipSelected() {
        setSelected(!isSelected());
    }

    public double getHexHeight() {
        return this.hexHeight;
    }

    public double getHexWidth() {
        return this.hexWidth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    @Override
    public String toString() {
        return String.format("CenterX=%8.3f, CenterY=%8.3f, size=%8.3f", this.centerX, this.centerY, this.BASE_SIZE);
    }

    public void addNeighbour(HexField neighbour) {
        if (neighbour == null)
            return;
        this.neighbours.add(neighbour);
    }

    public HexPolygon getParent() {
        return parent;
    }

    public void setParent(HexPolygon parent) {
        this.parent = parent;
    }
}
