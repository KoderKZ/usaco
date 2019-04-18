/*
 ID: xfrostb1
 LANG: JAVA
 TASK: maze1
 */
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class maze1 {
    static TreeMap<Integer, String> dictionary = new TreeMap<Integer, String>();
    static boolean[][] n, s, e, w;
    static String[][] maze;
    static ArrayList<Point> exits = new ArrayList<>();
    static int[][] ret;
    static boolean[][] visited;
    static int x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        maze = new String[2*x+1][2*y+1];
        n = new boolean[x][y];
        s = new boolean[x][y];
        e = new boolean[x][y];
        w = new boolean[x][y];
        ret = new int[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                ret[i][j] = Integer.MAX_VALUE;
            }
        }
        visited = new boolean[x][y];
        for(int i = 0; i < 2*y+1; i++){
            String s = f.readLine();
            for(int j = 0; j < 2*x+1; j++){
                maze[j][i] = String.valueOf(s.charAt(j));
            }
        }
        for(int i = 1; i < 2*y+1; i += 2){
            for(int j = 1; j < 2*x+1; j += 2){
                n[(j-1)/2][(i-1)/2] = !maze[j][i-1].equals("-");
                s[(j-1)/2][(i-1)/2] = !maze[j][i+1].equals("-");
                e[(j-1)/2][(i-1)/2] = !maze[j-1][i].equals("|");
                w[(j-1)/2][(i-1)/2] = !maze[j+1][i].equals("|");
            }
        }
        for(int i = 1; i < 2*x+1; i+=2){
            if(maze[i][0].equals(" ")){
                exits.add(new Point((i-1)/2,0));
                n[(i-1)/2][0] = false;
            }
            if(maze[i][2*y].equals(" ")){
                exits.add(new Point((i-1)/2,y-1));
                s[(i-1)/2][y-1] = false;
            }
        }
        for(int i = 1; i < 2*y+1; i+=2){
            if(maze[0][i].equals(" ")){
                exits.add(new Point(0,(i-1)/2));
                e[0][(i-1)/2] = false;
            }
            if(maze[2*x][i].equals(" ")){
                exits.add(new Point(x-1,(i-1)/2));
                w[x-1][(i-1)/2] = false;
            }
        }
        for(int i = 0; i < exits.size(); i++){
            dfs(exits.get(i).x, exits.get(i).y, 1);
        }
        int r = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                r = Math.max(ret[i][j], r);
            }
        }
        out.println(r);
        out.close();
    }
    static void dfs(int X, int Y, int count){
        if(count < ret[X][Y]) visited[X][Y] = false;
        if (!visited[X][Y]) {
            ret[X][Y] = Math.min(ret[X][Y], count);
            visited[X][Y] = true;


            if (n[X][Y] && Y > 0) dfs(X, Y-1, count+1);
            if (s[X][Y] && Y < y-1) dfs(X, Y+1, count+1);
            if (e[X][Y] && X > 0) dfs(X-1, Y, count+1);
            if (w[X][Y] && X < x-1) dfs(X+1, Y, count+1);
        }

    }
}