/*
ID: xfrostb1
LANG: JAVA
TASK: camelot
*/

import java.io.*;
import java.util.*;

public class camelot {
    static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static ArrayList<Point> knights;
    static int[][][][] dist;
    static int R, C;
    static Point king;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int y = st.nextToken().charAt(0)-'A';
        king = new Point(Integer.parseInt(st.nextToken())-1, y);
        knights = new ArrayList<>();
        String s = f.readLine();
        while(s != null){
            st = new StringTokenizer(s);
            while(st.hasMoreTokens()){
                y = st.nextToken().charAt(0)-'A';
                knights.add(new Point(Integer.parseInt(st.nextToken())-1, y));
            }
            s = f.readLine();
        }

        out.println(solve());
        out.close();
    }

    static int solve(){
        init();

        int ret = Integer.MAX_VALUE;


        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                int move = move(new Point(i, j));
                ret = Math.min(move, ret);
            }
        }
        return ret;
    }

    static int move(Point dest){
        int ret = 0;
        for(Point i : knights){
            if(dist[dest.x][dest.y][i.x][i.y] == -1) return Integer.MAX_VALUE;
            ret += dist[dest.x][dest.y][i.x][i.y];
        }
        int temp = Math.max(Math.abs(king.x-dest.x), Math.abs(king.y-dest.y));
        for(int i = Math.max(0, king.x-2); i <= Math.min(R-1, king.x+2); i++){
            for(int j = Math.max(0, king.y-2); j <= Math.min(C-1, king.y+2); j++){
                for(Point k : knights){
                    //mark[dest.x][dest.y][rx][cy]
                    //												+ mark[rx][cy][knight.x][knight.y]
                    //												- mark[dest.x][dest.y][knight.x][knight.y]
                    //												+ Math.max(
                    //														Math.abs(king.x - rx),
                    //														Math.abs(king.y - cy)));
                    if(dist[k.x][k.y][i][j] != -1 && dist[k.x][k.y][dest.x][dest.y] != -1)
                        temp = Math.min(temp, Math.max(Math.abs(king.x-i), Math.abs(king.y-j))+dist[k.x][k.y][i][j]+dist[i][j][dest.x][dest.y]-dist[k.x][k.y][dest.x][dest.y]);
                }
            }
        }
        return ret+temp;
    }
    static void init(){
        dist = new int[R][C][R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                for(int k = 0; k < R; k++){
                    for(int l = 0; l < C; l++){
                        dist[i][j][k][l] = -1;
                    }
                }
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                minpath(new Point(i, j));
            }
        }



    }
    static void minpath(Point destination){
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(destination);
        dist[destination.x][destination.y][destination.x][destination.y] = 0;
        while (!queue.isEmpty()) {
            Point node = queue.poll();
            int x = node.x;
            int y = node.y;
            for (int i = 0; i < dx.length; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                if (xx >= 0 && xx < R && yy >= 0 && yy < C) {
                    if (dist[destination.x][destination.y][xx][yy] == -1
                            || dist[destination.x][destination.y][x][y] + 1 < dist[destination.x][destination.y][xx][yy]) {
                        dist[destination.x][destination.y][xx][yy] = dist[destination.x][destination.y][x][y] + 1;
                        dist[xx][yy][destination.x][destination.y] = dist[destination.x][destination.y][x][y] + 1;
                        queue.add(new Point(xx, yy));
                    }
                }
            }
        }

    }

    static class Point{
        int x, y;
        public Point(int X, int Y){
            x = X;
            y = Y;
        }
    }
}

///*
// ID: antonio13
// TASK: camelot
// LANG: JAVA
// */
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//
///**
// * The main task of this problem is find a node in graph, so every knights and
// * king could reach it with minimum steps. Here, knights and the king move as
// * them in the chess game, but one knight could carry the king to save king some
// * time.<br />
// *
// * Our algorithm here is to find the all of the minimum distance between any two
// * nodes in the grids. Then check if set out a knight to give king a ride would
// * save some time.
// * */
//public class camelot {
//
//    // Knight's move;
//    private int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
//    private int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
//    // save minimum distance
//    private int[][][][] mark;
//
//    public static void main(String[] args) throws Exception {
//        camelot main = new camelot();
//        main.run();
//        System.exit(0);
//    }
//
//    private void run() throws Exception {
//        // try {
//        BufferedReader in = new BufferedReader(new FileReader("camelot.in"));
//        PrintWriter out = new PrintWriter(new FileWriter("camelot.out"));
//        String[] str = in.readLine().split("\\s");
//        ArrayList<Position> knights = new ArrayList<Position>();
//        int R = Integer.parseInt(str[0]);
//        int C = Integer.parseInt(str[1]);
//        str = in.readLine().split("\\s");
//        Position king = new Position(Integer.parseInt(str[1]) - 1,
//                str[0].charAt(0) - 'A');
//        // Better check if there is any knight followed in the same line.
//        // Though no test tests this one case.
//        if (str.length > 2) {
//            for (int i = 2; i < str.length; i += 2) {
//                knights.add(new Position(Integer.parseInt(str[i + 1]) - 1,
//                        str[i].charAt(0) - 'A'));
//            }
//        }
//        // Add all the knights to the ArrayList;
//        String strline = null;
//        while ((strline = in.readLine()) != null) {
//            str = strline.split("\\s");
//            for (int i = 0; i < str.length; i += 2) {
//                knights.add(new Position(Integer.parseInt(str[i + 1]) - 1,
//                        str[i].charAt(0) - 'A'));
//            }
//        }
//        in.close();
//        out.write(String.format("%d\n", moveAll(R, C, king, knights)));
//        // System.out.println(moveAll(R, C, king, knights));
//        out.close();
//        // } catch (Exception e) {
//        // System.out.println("Error" + e.getMessage());
//        // }
//    }
//
//    /**
//     * Calculate all the shortest path between any two nodes in the grids.
//     * mark[x1][y1][x2][y2] represents shortest path from (x1, y1) to (x2, y2);
//     * */
//    private void initMoves(int R, int C) {
//        mark = new int[R][C][R][C];
//        for (int a = 0; a < R; a++)
//            for (int b = 0; b < C; b++)
//                for (int c = 0; c < R; c++)
//                    for (int d = 0; d < C; d++) {
//                        mark[a][b][c][d] = -1;
//                    }
//
//        for (int r = 0; r < R; r++) {
//            for (int c = 0; c < C; c++)
//                move2Dest(R, C, new Position(r, c));
//        }
//    }
//
//    /**
//     * Find the minimum steps to reach some node in the grid.<br />
//     * Enumerate all of the possible nodes in the grid, check which one has the
//     * minimum steps;
//     */
//    private int moveAll(int R, int C, Position king, ArrayList<Position> knights) {
//        int moves = Integer.MAX_VALUE;
//        initMoves(R, C);
//        for (int r = 0; r < R; r++)
//            for (int c = 0; c < C; c++) {
//                int tmp = minSteps(R, C, new Position(r, c), king, knights);
//                if (tmp < moves) {
//                    moves = tmp;
//                }
//            }
//        return moves;
//    }
//
//    /**
//     * Find if sending one knight could help king save some time. HOWEVER, it's
//     * always better for king to move to some point for knight to reach easier.
//     * This move could be (0, 0).
//     * */
//    private int minSteps(int R, int C, Position dest, Position king,
//                         ArrayList<Position> knights) {
//        int moves = 0;
//        for (Position knight : knights) {
//            if (mark[dest.x][dest.y][knight.x][knight.y] == -1) {
//                return Integer.MAX_VALUE;
//            }
//            moves += mark[dest.x][dest.y][knight.x][knight.y];
//        }
//        int temp = Math.max(Math.abs(dest.x - king.x),
//                Math.abs(dest.y - king.y));
//        for (int rx = Math.max(0, king.x - 2); rx <= Math
//                .min(R - 1, king.x + 2); rx++) {
//            for (int cy = Math.max(0, king.y - 2); cy <= Math.min(C - 1,
//                    king.y + 2); cy++) {
//                for (Position knight : knights) {
//                    if (mark[dest.x][dest.y][rx][cy] != -1
//                            && mark[rx][cy][knight.x][knight.y] != -1)
//                        temp = Math
//                                .min(temp,
//                                        mark[dest.x][dest.y][rx][cy]
//                                                + mark[rx][cy][knight.x][knight.y]
//                                                - mark[dest.x][dest.y][knight.x][knight.y]
//                                                + Math.max(
//                                                Math.abs(king.x - rx),
//                                                Math.abs(king.y - cy)));
//                    else {
//                    }
//                }
//            }
//        }
//        return moves + temp;
//    }
//
//    /**
//     * Calculate the shortest distance between all the nodes on the grid to dest
//     * node.
//     *
//     * @param R
//     *            The number of row for grid;
//     * @param C
//     *            The number of column for grid;
//     *
//     * @param dest
//     *            The destination / source node in the graph.
//     * */
//    private void move2Dest(int R, int C, Position dest) {
//        Queue<Position> queue = new LinkedList<Position>();
//        queue.add(dest);
//        mark[dest.x][dest.y][dest.x][dest.y] = 0;
//        while (!queue.isEmpty()) {
//            Position node = queue.poll();
//            int x = node.x;
//            int y = node.y;
//            for (int i = 0; i < dx.length; i++) {
//                int xx = x + dx[i];
//                int yy = y + dy[i];
//                if (xx >= 0 && xx < R && yy >= 0 && yy < C) {
//                    if (mark[dest.x][dest.y][xx][yy] == -1
//                            || mark[dest.x][dest.y][x][y] + 1 < mark[dest.x][dest.y][xx][yy]) {
//                        mark[dest.x][dest.y][xx][yy] = mark[dest.x][dest.y][x][y] + 1;
//                        mark[xx][yy][dest.x][dest.y] = mark[dest.x][dest.y][x][y] + 1;
//                        queue.add(new Position(xx, yy));
//                    }
//                }
//            }
//        }
////        for(int i = 0; i < R; i++){
////            for(int j = 0; j < C; j++){
////                for(int k = 0; k < R; k++){
////                    for(int l = 0; l < C; l++){
////                        System.out.print(mark[i][j][k][l]);
////                    }
////                }
////            }
////        }
////        System.out.println();
//    }
//
//    /**
//     * Easy to insert a node to Queue, instead of maintaining a two-dimensional
//     * array for x and y;
//     * */
//    class Position {
//        public int x;
//        public int y;
//
//        public Position(int a, int b) {
//            this.x = a;
//            this.y = b;
//        }
//
//        public String toString() {
//            return String.format("(%d, %d)", x, y);
//        }
//    }
//}