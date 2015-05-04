import java.util.Formatter;
import java.util.Locale;

/**
 * Michael Shihrer
 * CSCI 232, Lab 4
 */
public class Point {

    private double X;
    private double Y;

    public Point()
    {
        X = 0.0;
        Y = 0.0;
    }
    public Point(double x, double y)
    {
        X = x;
        Y = y;
    }

    public double getX()
    {
        return X;
    }

    public double getY()
    {
        return Y;
    }

    @Override
    public String toString()
    {
        //Returns formatted string to look like:
        //(X.X,Y.Y)
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format("(%.1f,%.1f)", X, Y);
        return sb.toString();
    }
}
