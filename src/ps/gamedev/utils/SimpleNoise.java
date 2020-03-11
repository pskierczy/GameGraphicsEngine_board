package ps.gamedev.utils;

import java.util.Map;
import java.util.TreeMap;

public class SimpleNoise {
    private Map<Point, Double> heightMap;

    public SimpleNoise() {
        heightMap = new TreeMap<>();
    }

    public boolean addToMap(double x, double y, double h) {
        Point p = new Point(x, y);
        if (heightMap.containsKey(p))
            return false;
        heightMap.put(p, h);
        return true;
    }

    public double getNoise(double x, double y) {
        double height = 0.0;
        double totalX = 0.0, totalY = 0.0;
        double r;

        for (Point p :
                heightMap.keySet()) {

        }
        return 0;
    }


    private double lerp(double x, Point p1, Point p2) {
        return (p2.y - p1.y) / (p2.x - p1.x) * x + p1.y;
    }
}
