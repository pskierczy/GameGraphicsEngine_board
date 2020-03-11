package ps.gamedev.Board;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.omg.CORBA.WStringSeqHelper;

import java.util.List;

public class HexField_withText
        extends Group {
    private double centerX, centerY, size, h, r;
    private List<HexField_withText> neighbours;
    static final double rRatio = 0.86602540378;  //cos(30)
    static final double alpha = 1.0471975512;  //PI/3.0
    static final Color BASE_FILL = Color.YELLOW, BASE_STROKE = Color.BLACK;
    static final Color SELECTED_FILL = Color.GREEN, SELECTED_STROKE = Color.RED, SELECTED_NEIGHBOUR = Color.LIMEGREEN;
    private boolean selected;

    private HexField hexField;
    private Label text;
    private Rectangle border;

    public static HexField_withText FlatUnitary() {
        return new HexField_withText(0, 0, 1, 0, 0, 0);
    }

    public static HexField_withText TipUnitary() {
        return new HexField_withText(0, 0, 1, -30, 0, 0);
    }


    public HexField_withText() {
        this(0, 0, 1, 0, 0, 0);
    }

    public HexField_withText(double centerX, double centerY, double size) {
        this(centerX, centerY, size, 0.0, 0, 0);
    }

    public HexField_withText(double centerX, double centerY, double size, double angleInDegrees, int row, int column) {
        super();
        this.centerX = centerX;
        this.centerY = centerY;
        this.size = size;

        this.hexField = new HexField(0, 0, size, angleInDegrees);

        this.setLayoutX(this.centerX);
        this.setLayoutY(this.centerY);
        this.setTranslateX(this.hexField.getW() / 2.0);
        this.setTranslateY(this.hexField.getH() / 2.0);

//        this.border = new Rectangle(-this.hexField.getW() / 2, -this.hexField.getH() / 2, hexField.getW(), this.hexField.getH());
//        this.border.setFill(Color.TRANSPARENT);
//        this.border.setStrokeWidth(0.5);
//        this.border.setStroke(Color.BLUE);
//        this.getChildren().add(this.border);


        this.text = new Label();
        this.text.setPrefWidth(this.hexField.getW());
        this.text.setPrefHeight(this.hexField.getH() / 2);
        this.text.setLayoutX(-this.hexField.getW() / 2.0);
        this.text.setLayoutY(-this.hexField.getH() / 4.0);
        this.text.setAlignment(Pos.CENTER);
        this.text.setFont(Font.font("Courier New", size / 2.5));
        setTextRowColumn(row, column);
        this.getChildren().addAll(this.hexField, this.text);
    }

    public List<HexField> getNeighbours() {
        return this.hexField.getNeighbours();
    }

    public HexField getHexField() {
        return this.hexField;
    }

    public void initHandlers() {
        this.hexField.initHandlers();
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setTextRowColumn(int row, int column) {
        setTextRowColumn(row, column, 0);
    }

    public void setTextRowColumn(int row, int column, int ID) {
        setText(String.format("[%d,%d]\nID:%d", row, column, ID));
    }

    @Override
    public String toString() {
        return String.format("CenterX=%8.3f, CenterY=%8.3f, size=%8.3f Text:%s", this.centerX, this.centerY, this.size, this.text.getText());
    }

    public void addNeighbour(HexField neighbour) {
        this.hexField.addNeighbour(neighbour);
    }
}
