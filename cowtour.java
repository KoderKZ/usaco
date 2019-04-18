/*
 ID: xfrostb1
 LANG: JAVA
 TASK: cowtour
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;



public class cowtour {

    static List<Point> list;
    static char[][] map;
    static int N;

    static double[][] cost;
    static double[] maxd;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("cowtour.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("cowtour.out"));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<Point>();
        map = new char[N][N];

        cost = new double[N][N];
        maxd = new double[N];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("\\s");
            Double x = Double.parseDouble(str[0]);
            Double y = Double.parseDouble(str[1]);
            list.add(new Point(x, y));
        }

        // Initialize the cost;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '1')
                    cost[i][j] = list.get(i).distance(list.get(j));
                else
                    cost[i][j] = Double.MAX_VALUE;
                if (i == j)
                    cost[i][j] = 0;
            }
        }

        // Floyd to calculate the shortest dist between any two points;
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        // calculate the max diameter of the connected vertices;
        double max1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cost[i][j] != Double.MAX_VALUE) {
                    maxd[i] = Math.max(maxd[i], cost[i][j]);
                    max1 = Math.max(max1, maxd[i]);
                }
            }
        }

        // after trying to add the new edge, find the minimum cost;
        double max2 = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cost[i][j] == Double.MAX_VALUE) {
                    max2 = Math.min(max2, maxd[i] + maxd[j]
                            + list.get(i).distance(list.get(j)));
                }
            }
        }

        // System.out.println(String.format("%.6f", Math.max(max1, max2)));
        out.write(String.format("%.6f", Math.max(max1, max2)) + "\n");
        out.close();
    }

}

class Point {
    public double x;
    public double y;

    public Point(double xx, double yy) {
        this.x = xx;
        this.y = yy;
    }

    public double distance(Point p) {
        return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y)
                * (this.y - p.y));
    }
}
