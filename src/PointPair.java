import java.util.Formatter;
import java.util.Locale;

/**
 * Michael Shihrer
 * CSCI 232, Lab 4
 */
public class PointPair {
    private Point pointA;
    private Point pointB;
    private double distance;

    public PointPair()
    {
        pointA = new Point();
        pointB = new Point();

        distance = Double.POSITIVE_INFINITY;
    }

    public PointPair(Point pointA, Point pointB)
    {
        this.pointA = pointA;
        this.pointB = pointB;

        findDistance();
    }

    private void findDistance()
    {
        distance = Math.sqrt((pointA.getX()-pointB.getX())*(pointA.getX()-pointB.getX()) + (pointA.getY() - pointB.getY())*(pointA.getY() - pointB.getY()));
    }

    public Point getPointA()
    {
        return pointA;
    }

    public Point getPointB()
    {
        return pointB;
    }

    public double getDistance()
    {
        return distance;
    }

    @Override
    public String toString()
    {
        //Returns formatted string to look like:
        //P1:(X1,Y1), P2:(X2,Y2), Distance: D
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format("P1:%s, P2:%s, Distance: %.1f", pointA, pointB, distance);
        return sb.toString();
    }

}
