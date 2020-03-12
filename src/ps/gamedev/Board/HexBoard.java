package ps.gamedev.Board;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HexBoard
        extends Group {
    private int rows, columns;
    private boolean isoView;
    private final boolean DEBUG = false;
    private Class tClass;

    public HexBoard(double centerX, double centerY, double hexSize, int rows, int columns, boolean isoView) {
        super();
        this.rows = rows;
        this.columns = columns;
        this.isoView = isoView;
        final double wSpacing;
        final double hSpacing;

        if (DEBUG) {
            tClass = HexField_withText.class;
            wSpacing = HexField.FlatUnitary().getHexWidth() * hexSize;
            hSpacing = HexField.FlatUnitary().getHexHeight() * hexSize * 0.75;
        } else if (isoView) {
            tClass = HexFieldISO.class;
            wSpacing = HexFieldISO.FlatUnitary().getW() * hexSize;
            hSpacing = HexFieldISO.FlatUnitary().getH() * hexSize * 0.75;
        } else {
            tClass = HexField.class;
            wSpacing = HexField.FlatUnitary().getHexWidth() * hexSize;
            hSpacing = HexField.FlatUnitary().getHexWidth() * hexSize * 0.75;
        }

        double x0 = centerX - wSpacing * columns * 0.5;
        double y0 = centerY - hSpacing * rows * 0.5;
        double x, y;


        for (int row = 0; row < rows; row++) {
            y = y0 + hSpacing * row;
            for (int column = 0; column < columns; column++) {
                x = x0 + wSpacing * column + 0.5 * wSpacing * (row % 2);

                //HEX WITH TEXT FOR TEST PURPOSES ONLY
                if (DEBUG) {
                    HexField_withText hexField = new HexField_withText(x, y, hexSize);
                    hexField.setTextRowColumn(row, column, idFromRowColumn(row, column));
                    hexField.initHandlers();
                    this.getChildren().add(hexField);
                }
                ////*****************
                else {
                    if (this.isoView) {
                        HexFieldISO hexField = new HexFieldISO(x, y, hexSize, hexSize * ((Math.sin(0.01 * x) + Math.cos(0.02 * y)) + 3.0));
                        hexField.initHandlers();
                        this.getChildren().add(hexField);
                        //this.scaleYProperty(0.5);
                    } else {
                        HexField hexField = new HexField(x, y, hexSize);
                        hexField.initHandlers();
                        this.getChildren().add(hexField);
                    }
                }
            }
        }
        setupNeighbours();


        //TODO:REMOVE FOR RELEASE
        Rectangle border = new Rectangle(-hexSize * columns / 2 + centerX, -hexSize * rows / 2 + centerY, hexSize * columns, hexSize * rows);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.RED);
        border.setStrokeWidth(0.5);
        border.setMouseTransparent(true);
        this.getChildren().add(border);
    }

    /*
     public HexBoard_backup(double centerX, double centerY, double hexSize, int rows, int columns, boolean isoView) {
        super();
        this.rows = rows;
        this.columns = columns;
        final double wSpacing = HexField.FlatUnitary().getW() * hexSize;
        final double hSpacing = HexField.FlatUnitary().getH() * hexSize * 0.75;

        double x0 = centerX - wSpacing * columns * 0.5;
        double y0 = centerY - hSpacing * rows * 0.5;
        double x, y;

        for (int row = 0; row < rows; row++) {
            y = y0 + hSpacing * row;
            for (int column = 0; column < columns; column++) {
                x = x0 + wSpacing * column + 0.5 * wSpacing * (row % 2);

                //HEX WITH TEXT FOR TEST PURPOSES ONLY
                if (DEBUG) {
                    HexField_withText hexField = new HexField_withText(x
                            , y, hexSize);
                    hexField.setTextRowColumn(row, column, idFromRowColumn(row, column));
                    hexField.initHandlers();
                    this.getChildren().add(hexField);
                }
                ////*****************
                else {
                    HexField hexField = new HexField(x
                            , y, hexSize);
                    hexField.initHandlers();
                    this.getChildren().add(hexField);
                }
            }
        }
        setupNeighbours();


        //TODO:REMOVE FOR RELEASE
        Rectangle border = new Rectangle(-hexSize * columns / 2 + centerX, -hexSize * rows / 2 + centerY, hexSize * columns, hexSize * rows);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.RED);
        border.setStrokeWidth(0.5);
        border.setMouseTransparent(true);
        this.getChildren().add(border);
    }
    * */

    public HexBoard(double centerX, double centerY, double hexSize, int rows, int columns) {
        this(centerX, centerY, hexSize, rows, columns, false);
    }

    private void setupNeighbours() {
        int elemsCount = this.getChildren().size();
        // as start due to border
        //neighbours are (row and column numbering from 0)
        //  odd row  | even row
        // [r-1,c  ] | [r-1,c-1]
        // [r-1,c+1] | [r-1,c  ]
        // [r  ,c-1] | [r  ,c-1]
        // [r  ,c+1] | [r  ,c+1]
        // [r+1,c  ] | [r+1,c-1]
        // [r+1,c+1] | [r+1,c  ]


        for (int i = 0; i < elemsCount; i++)
            genericAddNeighbour(i);

    }

    private int idToColumn(int id) {
        return id % this.columns;
    }

    private int idToRows(int id) {
        return id / columns;
    }

    private int idFromRowColumn(int row, int column) {
        //ID=row+column*columns
        //row=ID mod columns
        //column=ID%columns
        return row * columns + column;
    }

    private <T> void genericAddNeighbour(int id) {
        int row = idToRows(id);
        int column = idToColumn(id);
        int evenRow = (row % 2);

        if (tClass.equals(HexField.class)) {
            HexField currentField = getField(row, column);
            currentField.addNeighbour(getField(row - 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row - 1, column + evenRow));
            currentField.addNeighbour(getField(row, column - 1));
            currentField.addNeighbour(getField(row, column + 1));
            currentField.addNeighbour(getField(row + 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row + 1, column + evenRow));
            System.out.println(String.format("NODE:%d [%d,%d] n:%d", id, row, column, currentField.getNeighbours().size()));
            return;
        }
        if (tClass.equals(HexFieldISO.class)) {
            HexFieldISO currentField = getField(row, column);
            currentField.addNeighbour(getField(row - 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row - 1, column + evenRow));
            currentField.addNeighbour(getField(row, column - 1));
            currentField.addNeighbour(getField(row, column + 1));
            currentField.addNeighbour(getField(row + 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row + 1, column + evenRow));
            System.out.println(String.format("NODE:%d [%d,%d] n:%d", id, row, column, currentField.getNeighbours().size()));
        }
        if (tClass.equals(HexField_withText.class)) {
            HexField_withText currentField = getField(row, column);
            currentField.addNeighbour(getField(row - 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row - 1, column + evenRow));
            currentField.addNeighbour(getField(row, column - 1));
            currentField.addNeighbour(getField(row, column + 1));
            currentField.addNeighbour(getField(row + 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row + 1, column + evenRow));
            System.out.println(String.format("NODE:%d [%d,%d] n:%d", id, row, column, currentField.getNeighbours().size()));
        }
    }

//    public HexField getField(int row, int column) {
//        if (row < 0 || row >= rows || column < 0 || column >= columns)
//            return null;
//        if (DEBUG)
//            return ((HexField_withText) this.getChildren().get(idFromRowColumn(row, column))).getHexField();
//        else if (this.isoView)
//            return (HexFieldISO) this.getChildren().get(idFromRowColumn(row, column));
//        else
//            return (HexField) this.getChildren().get(idFromRowColumn(row, column));
//    }

    public <T> T getField(int row, int column) {
        Class<T> classType = tClass;
        if (row < 0 || row >= rows || column < 0 || column >= columns)
            return null;
        if (classType.isInstance(HexField_withText.class))
            return classType.cast(((HexField_withText) this.getChildren().get(idFromRowColumn(row, column))).getHexField());
        else
            return classType.cast(this.getChildren().get(idFromRowColumn(row, column)));
    }

//    public HexField_withText getField(int row, int column) {
//        if (row < 0 || row >= rows || column < 0 || column >= columns)
//            return null;
//
//        return (HexField_withText) this.getChildren().get(idFromRowColumn(row, column));
//    }
//
//    public HexFieldISO getField(int row, int column) {
//        if (row < 0 || row >= rows || column < 0 || column >= columns)
//            return null;
//
//        return (HexFieldISO) this.getChildren().get(idFromRowColumn(row, column));
//    }
}
