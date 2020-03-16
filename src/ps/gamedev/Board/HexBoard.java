package ps.gamedev.Board;

import java.util.ArrayList;
import java.util.List;

public class HexBoard {
    private int rows, columns;
    private final boolean DEBUG = false;
    private double hexSize, centerX, centerY;
    private List<HexField> board;
    private List<HexPolygon> boardGrx;

    public HexBoard(double centerX, double centerY, double hexSize, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.centerX = centerX;
        this.centerY = centerY;
        this.hexSize = hexSize;
        this.board = new ArrayList<>(rows * columns + 1);
        this.boardGrx = new ArrayList<>(rows * columns + 1);
        initializeBoard();
    }

    private void initializeBoard() {
        final double wSpacing;
        final double hSpacing;

        wSpacing = HexField.FlatUnitary().getHexWidth() * hexSize;
        hSpacing = HexField.FlatUnitary().getHexWidth() * hexSize * 0.75;

        double x0 = centerX - wSpacing * columns * 0.5;
        double y0 = centerY - hSpacing * rows * 0.5;
        double x, y;
        for (int row = 0; row < rows; row++) {
            y = y0 + hSpacing * row;
            for (int column = 0; column < columns; column++) {
                x = x0 + wSpacing * column + 0.5 * wSpacing * (row % 2);
                HexField hexField = new HexField(x, y, hexSize);
                board.add(hexField);
            }
        }
        setupNeighbours();

        HexPolygon hexPolygon;
        for (HexField hf :
                this.board) {
            hexPolygon = new HexPolygon(hf);
            this.boardGrx.add(hexPolygon);
        }
    }

    private void setupNeighbours() {
        int elemsCount = this.board.size();
        // as start due to border
        //neighbours are (row and column numbering from 0)
        //  odd row  | even row
        // [r-1,c  ] | [r-1,c-1]
        // [r-1,c+1] | [r-1,c  ]
        // [r  ,c-1] | [r  ,c-1]
        // [r  ,c+1] | [r  ,c+1]
        // [r+1,c  ] | [r+1,c-1]
        // [r+1,c+1] | [r+1,c  ]
        HexField currentField;
        int row, column, evenRow;

        for (int i = 0; i < elemsCount; i++) {
            row = idToRow(i);
            column = idToColumn(i);
            evenRow = (row % 2);

            currentField = getField(row, column);
            currentField.addNeighbour(getField(row - 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row - 1, column + evenRow));
            currentField.addNeighbour(getField(row, column - 1));
            currentField.addNeighbour(getField(row, column + 1));
            currentField.addNeighbour(getField(row + 1, column - 1 + evenRow));
            currentField.addNeighbour(getField(row + 1, column + evenRow));
            System.out.println(String.format("HEX:%d [%d,%d] n:%d", i, row, column, currentField.getNeighbours().size()));
        }
    }

    private int idToColumn(int id) {
        return id % this.columns;
    }

    private int idToRow(int id) {
        return id / columns;
    }

    private int idFromRowColumn(int row, int column) {
        //ID=row+column*columns
        //row=ID mod columns
        //column=ID%columns
        return row * columns + column;
    }

    private HexField getField(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns)
            return null;
        return this.board.get(idFromRowColumn(row, column));
    }

    public List<HexField> getBoard() {
        return this.board;
    }

    public List<HexPolygon> getHexPolys() {
        return this.boardGrx;
    }
}
