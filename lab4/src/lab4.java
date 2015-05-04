import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Michael Shihrer
 * CSCI 232, Lab 4
 */
public class lab4 {

    private static Scanner scanner = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String args[])
    {
        //Array of unsorted Points
        ArrayList<Point> unsortedPoints = new ArrayList<Point>();

        while(unsortedPoints.size() == 0) {
            System.out.println("Enter menu number to select:");
            System.out.println("1. Use hardcoded points from lab example.");
            System.out.println("2. Use randomized points.");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    unsortedPoints = hardCodedPoints();
                    break;
                case 2:
                    unsortedPoints = randomPoints();
                    break;
                default:
                    System.out.println("\tThat is not a valid option");
                    break;
            }
        }

        //Create instance of ClosestPair class, send unsortedPoints array
        ClosestPair closestPair = new ClosestPair(unsortedPoints);
    }

    private static ArrayList<Point> randomPoints()
    {
        int max = 100;
        int min = -100;
        System.out.println("\tEnter the number of points to generate:");
        int numberOfPoints = scanner.nextInt();
        ArrayList<Point> randomPoints = new ArrayList<Point>();

        for(int i = 0; i < numberOfPoints; i++)
        {
            randomPoints.add(new Point(rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min));
        }

        return randomPoints;
    }
    private static ArrayList<Point> hardCodedPoints()
    {
        //Array of unsorted Points
        Point[] unsortedPoints = new Point[12];

        //hardcoded points - I shifted the values around so that they're not sorted
        //(2.0,7.0) (4.0,13.0) (5.0,8.0) (10.0,5.0) (14.0,9.0) (15.0,5.0) (17.0,7.0) (19.0,10.0) (22.0,7.0)
        //(25.0,10.0) (29.0,14.0) (30.0,2.0)
        unsortedPoints[10] = new Point(2.0,7.0);
        unsortedPoints[8] = new Point(4.0,13.0);
        unsortedPoints[3] = new Point(5.0,8.0);
        unsortedPoints[2] = new Point(10.0,5.0);
        unsortedPoints[7] = new Point(14.0,9.0);
        unsortedPoints[5] = new Point(15.0,5.0);
        unsortedPoints[6] = new Point(17.0,7.0);
        unsortedPoints[4] = new Point(19.0,10.0);
        unsortedPoints[1] = new Point(22.0,7.0);
        unsortedPoints[9] = new Point(25.0,10.0);
        unsortedPoints[0] = new Point(29.0,14.0);
        unsortedPoints[11] = new Point(30.0,2.0);
        //unsortedPoints[12] = new Point(30.1,2.0);

        return new ArrayList<Point>(Arrays.asList(unsortedPoints));
    }
}
