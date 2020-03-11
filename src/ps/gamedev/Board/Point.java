package ps.gamedev.Board;

import java.util.ArrayList;
import java.util.List;

public class Point {
    double x, y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void offset(double x, double y) {
        this.setX(getX() + x);
        this.setY(getY() + y);
    }

    public Point offsetNew(double x, double y) {
        return new Point(getX() + x, getY() + y);
    }
}
