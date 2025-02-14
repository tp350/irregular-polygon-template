import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.concurrent.TimeUnit;

import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    public IrregularPolygon() {}

    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint); //point to irregular polygon
    }

    public double perimeter() {
        double perimeter = 0.0;
        int n = myPolygon.size();
        for (int i = 0; i < n; i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % n);
            perimeter += p1.distance(p2);
        }
        return perimeter;
    }

    public double area() {
        double area = 0.0;
        int n = myPolygon.size();
        for (int i = 0; i < n; i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % n);
            area += p1.getX() * p2.getY() - p1.getY() * p2.getX();
        }
        return Math.abs(area) / 2.0;
    }
    public void draw()
    {
        try {
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            Point2D.Double firstPoint = myPolygon.get(0);
            pen.move(firstPoint.getX(), firstPoint.getY());
            for (int i = 1; i < myPolygon.size(); i++) {
                Point2D.Double point = myPolygon.get(i);
                pen.drawTo(point.getX(), point.getY());
            }
            pen.drawTo(firstPoint.getX(), firstPoint.getY());
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
