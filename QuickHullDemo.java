import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Class to demonstrate quick hull algorithm
 * 
 * Usage:
 * 1. enter file path on command line: java QuickHullDemo /path/to/file.txt 
 * 2. enter file path at prompt
 * 
 */
public final class QuickHullDemo {
    
    static String inputFileName;
    static List<Point> inputPoints;
    static List<Point> convexHullPoints;
    
    private QuickHullDemo() {}; // prevent instantiation

    public static void main(String[] args) {
        
        // get file path from command line or prompt
        if (args.length != 1) {
            System.out.print("Enter file path: ");
            try (Scanner in = new Scanner(System.in)) {
                inputFileName = in.next().strip();
            }
        } else
            inputFileName = args[0];
        
        // create file object if it exists
        File file = new File(inputFileName);
        
        // get points from file and populate array
        inputPoints = new ArrayList<>();
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] coords = line.strip().split(" ");
                inputPoints.add(
                        new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            System.exit(1);
        }
        
        // find convex hull
        quickHull(inputPoints);
        
        // print points of convex hull
        printPointsList(convexHullPoints);
    }
    
    static void quickHull(List<Point> points) {
        convexHullPoints = new ArrayList<>();
        
        // find the leftmost and rightmost points A and B
        points.sort((p1, p2) -> ((Double) p1.x).compareTo(p2.x)); // sort by x value
        Point A = points.get(0); // smallest x value
        Point B = points.get(points.size()-1); // largest x value
        
        // add points A and B to convexHull
        convexHullPoints.add(A);
        convexHullPoints.add(B);
        
        // find set of points on either side of line AB
        List<Point> s1 = new ArrayList<>();
        List<Point> s2 = new ArrayList<>();
        for (Point P : points) {
            if ((A.x-B.x)*(P.y-B.y)-(A.y-B.y)*(P.x-B.x) > 0)
                s1.add(P); // left of AB
            else if ((A.x-B.x)*(P.y-B.y)-(A.y-B.y)*(P.x-B.x) < 0)
                s2.add(P); // right of AB
        }
        
        // find points of convex hull from s1 and s2
        findHull(s1, A, B);
        findHull(s2, B, A);
    }
    
    static void findHull(List<Point> points, Point A, Point B) {
        if (!points.isEmpty()) { // empty is recursive base case

            // find the point C which is farthest from the line AB
            double m = (B.y - A.y) / (B.x - A.x); // slope
            double b = A.y - m * A.x; // y-intercept
            double maxDistance = 0.0;
            Point C = A;

            for (Point p : points) {
                double distance = Math.abs((-m*p.x + p.y - b) / Math.sqrt(m*m + 1));
                if (distance > maxDistance) {
                    maxDistance = distance;
                    C = p;
                }
            }
            
            // add C to convex hull
            convexHullPoints.add(C);

            // find s1 = points to left of AC, s2 = points to left of CB
            List<Point> s1 = new ArrayList<>();
            List<Point> s2 = new ArrayList<>();
            for (Point p : points) {
                if ((A.x-C.x)*(p.y-C.y)-(A.y-C.y)*(p.x-C.x) > 0)
                    s1.add(p); // left side of line AC
                else if ((C.x-B.x)*(p.y-B.y)-(C.y-B.y)*(p.x-B.x) > 0)
                    s2.add(p); // left side of line CB
            }

            // recursively find remaining points of convex hull
            findHull(s1, A, C);
            findHull(s2, C, B);
        }
    }
    
    static void printPointsList(List<Point> points) {
        for (Point p : points)
            System.out.print((int)p.x + " " + (int)p.y + "\n");
    }
}

class Point {
    
    double x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}