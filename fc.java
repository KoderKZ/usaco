/*
ID: xfrostb1
LANG: JAVA
TASK: fc
*/

import java.io.*;
import java.util.*;

public class fc {
    static Vector<Point> hull;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fc.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));
        double ret = 0;
        int N = Integer.parseInt(f.readLine());
        Point[] points = new Point[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            points[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }
        convexHull(points, N);
        for(int i = 0; i < hull.size(); i++){
            if(i < hull.size()-1) {
                ret += hull.get(i).distance(hull.get(i + 1));
            }else ret+= hull.get(i).distance(hull.get(0));

        }

        double output = Math.round(ret*100)/(double)100;
        if(String.valueOf(output).indexOf('.')==String.valueOf(output).length()-2){out.println(output+"0");}
        else out.println(output);
        out.close();

    }
    public static int orientation(Point p, Point q, Point r)
    {
        double val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;  // collinear
        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    // Prints convex hull of a set of n points.
    public static void convexHull(Point points[], int n)
    {
        // There must be at least 3 points
        if (n < 3) return;

        // Initialize Result
        hull = new Vector<Point>();

        // Find the leftmost point
        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[l].x)
                l = i;

        // Start from leftmost point, keep moving
        // counterclockwise until reach the start point
        // again. This loop runs O(h) times where h is
        // number of points in result or output.
        int p = l, q;
        do
        {
            // Add current point to result
            hull.add(points[p]);

            // Search for a point 'q' such that
            // orientation(p, x, q) is counterclockwise
            // for all points 'x'. The idea is to keep
            // track of last visited most counterclock-
            // wise point in q. If any point 'i' is more
            // counterclock-wise than q, then update q.
            q = (p + 1) % n;

            for (int i = 0; i < n; i++)
            {
                // If i is more counterclockwise than
                // current q, then update q
                if (orientation(points[p], points[i], points[q])
                        == 2)
                    q = i;
            }

            // Now q is the most counterclockwise with
            // respect to p. Set p as q for next iteration,
            // so that q is added to result 'hull'
            p = q;

        } while (p != l);  // While we don't come to first
        // point

        // Print Result
        for (Point temp : hull)
            System.out.println("(" + temp.x + ", " +
                    temp.y + ")");
    }
    static class Point
    {
        double x, y;
        Point(double x, double y){
            this.x=x;
            this.y=y;
        }
        double distance(Point o){
            return Math.sqrt(Math.pow(o.x-this.x, 2)+Math.pow(o.y-this.y,2));
        }
    }
}
