import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Michael Shihrer
 * CSCI 232, Lab 4
 */
public class ClosestPair {

    public ClosestPair(ArrayList<Point> unsortedPoints)
    {
        ArrayList<Point> xPoints = new ArrayList<Point>(unsortedPoints);
        ArrayList<Point> yPoints = new ArrayList<Point>(unsortedPoints);

        //Sort xPoints by X-coordinate - Collections.sort is supposed to use MergeSort
        //Should be O(nlogn) - If not, oops, and I would implement MergeSort
        Collections.sort(xPoints, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                if (a.getX() < b.getX())
                    return -1;
                else if (a.getX() > b.getX())
                    return 1;
                return 0;
            }
        });

        //Sort yPoints by Y-coordinate - Collections.sort is supposed to use MergeSort
        //Should be O(nlogn) - If not, oops, and I would implement MergeSort
        Collections.sort(yPoints, new Comparator<Point>(){
            public int compare(Point a, Point b) {
                if(a.getY() < b.getY())
                    return -1;
                else if(a.getY() > b.getY())
                    return 1;
                return 0;
            }
        });


        //Print inputs
        System.out.println("Input points:");
        for(Point xPoint : xPoints)
        {
            System.out.print(xPoint);
        }
        System.out.printf("\n---------------------------------------------\n");

        //Solve the problem!
        PointPair solution = findClosest(xPoints, yPoints, 0, unsortedPoints.size() - 1);

        //Print out results
        System.out.printf("---------------------------------------------\n");
        System.out.printf("Final result: %s", solution);
    }

    //xPoints are points sorted by X, yPoints are points sorted by Y.
    //lowBound is the left-most point of list in respect to original list.
    //highBound is the right-most point of list in respect to original list.
    //lowBound/highBound are only really used to keep track of what where lists are being divided in respect to the original
    private PointPair findClosest(ArrayList<Point> xPoints, ArrayList<Point> yPoints, int lowBound, int highBound)
    {
        System.out.printf("Solving Problem: Point[%d]...Point[%d]\n", lowBound,highBound);

        //base cases.
        if(xPoints.size() == 1) {
            //Return infinity if there's only one point being checked
            System.out.println("\tFound Result: INF");
            return new PointPair();
        }
        else if((xPoints.size()) == 2)
        {
            //When there are two points being checked, compare them directly
            PointPair delta = new PointPair(xPoints.get(0), xPoints.get(1));
            System.out.printf("\tFound result %s\n", delta);
            return delta;
        }

        //Find the point to divide problem in half
        int middle = xPoints.size()/2 + xPoints.size()%2;
        Point midPoint = xPoints.get(middle - 1);

        //Divide problem
        System.out.printf("\tDividing at point [%d]\n", lowBound + middle - 1);

        //Divide xPoints list into two
        ArrayList<Point> xPointsL = new ArrayList<Point>(xPoints.subList(0, middle));
        ArrayList<Point> xPointsR = new ArrayList<Point>(xPoints.subList(middle, xPoints.size()));

        //Divide yPoints list into two based on vert. line x = midPoint.getX()
        ArrayList<Point> yPointsL = new ArrayList<Point>();
        ArrayList<Point> yPointsR = new ArrayList<Point>();

        //Splits the Y points based on the vertical line (Left/Right).
        for (Point yPoint : yPoints) {
            if (yPoint.getX() <= midPoint.getX())
                yPointsL.add(yPoint);
            else
                yPointsR.add(yPoint);
        }

        //Solve subproblems
        PointPair deltaL = findClosest(xPointsL, yPointsL, lowBound, lowBound + middle - 1);
        PointPair deltaR = findClosest(xPointsR, yPointsR, highBound - middle + 1 + xPoints.size()%2, highBound);

        //Combine solutions to subproblems
        System.out.printf("Combining problems: Point[%d]...Point[%d] and Point[%d]...Point[%d]\n", lowBound, lowBound + middle - 1, highBound - middle + 1 + xPoints.size()%2, highBound);
        //Compare results of subproblems to find best
        PointPair delta = getMin(deltaL, deltaR);
        //Check for split pairs solution, compare to results of subproblems
        delta = getMin(delta, closestSplitPair(yPoints, delta, midPoint));

        System.out.printf("\tFound result: %s\n",delta);

        return delta;
    }

    //Function to find the shortest path between pairs that are split across the vertical line.
    private PointPair closestSplitPair(ArrayList<Point> yPoints, PointPair delta, Point midPoint)
    {
        PointPair bestPair = new PointPair();
        ArrayList<Point> splitPairs = new ArrayList<Point>();

        //Filter out points that are not within delta of vertical line - their distance will be greater
        //Check all points in y-Sorted points subproblem
        for (Point yPoint : yPoints) {
            //Check to see if point is within delta of vertical line
            if (Math.abs(yPoint.getX() - midPoint.getX()) < delta.getDistance()) {
                splitPairs.add(yPoint);
            }
        }

        //Check split pairs to see if they're closer together than bestPair - O(n) time
        //Check points that are split by line
        for(int i = 0; i < splitPairs.size() - 1; i++)
        {
            //Compare each left point to a point right of it
            for(int j = 1; j < Math.min(8, splitPairs.size() - i); j++) //Compare to nearby points
            {
                PointPair splitDelta = new PointPair(splitPairs.get(i), splitPairs.get(i + j));
                if(splitDelta.getDistance() < bestPair.getDistance())
                    bestPair = splitDelta;
            }
        }

        return bestPair;
    }

    //Utility function to determine which two PointPairs have the smallest distance
    private PointPair getMin(PointPair A, PointPair B)
    {
        if(A.getDistance() < B.getDistance())
            return A;
        else
            return B;
    }
}
