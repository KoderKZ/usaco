/*
ID: xfrostb1
LANG: JAVA
TASK: perimeter
*/

import java.io.*;
import java.util.*;

public class perimeter {
    static int N, maxa = 0, minp = Integer.MAX_VALUE, p;
    static boolean[][] visited, pvisited;
    static String[][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        N = Integer.parseInt(f.readLine());
        int max = 0;
        grid = new String[N][N];
        visited = new boolean[N][N];
        boolean alt = true;
        boolean check = true;
        for(int i = 0; i < N; i++){
            String s = f.readLine();
            for(int j = 0; j < N; j++){

                grid[j][i] = String.valueOf(s.charAt(j));
                boolean temp = s.charAt(j) == '#';
                if(i == 0 && j == 0) alt = s.charAt(j) == '#';
                else if(alt == temp) {
                    check = false;
                }else{
                    alt = temp;
                }
                if(s.charAt(j) == '#') max++;
            }
        }
        if(check) {
            out.println("1 4");
        }
        else if(max == 0) out.println("0 0");
        else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[j][i] && grid[j][i].equals("#")) {
                        p = 0;
                        pvisited = new boolean[N][N];
                        int amt = dfs(j, i);
                        if (amt == maxa) {
                            minp = Math.min(p, minp);
                        } else if (amt > maxa) {
                            minp = p;
                            maxa = amt;
                        }
                        max -= amt;
                    }
                    if (max < maxa) break;
                }
            }
            out.println(maxa + " " + minp);
            out.close();
        }
    }
    static int dfs(int x, int y){
        int amt = 0;
        if(!visited[x][y] && grid[x][y].equals("#")) {
            amt++;
            visited[x][y] = true;
            if(x > 0 && !visited[x-1][y]){
                amt+=dfs(x-1, y);
            }
            if(x < N-1 && !visited[x+1][y]){
                amt+=dfs(x+1, y);
            }
            if(y > 0 && !visited[x][y-1]){
                amt+=dfs(x, y-1);
            }
            if(y < N-1 && !visited[x][y+1]){
                amt+=dfs(x, y+1);
            }
            if(x == 0)p++;
            if(x == N-1) p++;
            if(y == 0) p++;
            if(y == N-1) p++;
            if(x > 0 && grid[x-1][y].equals(".")) p++;
            if(x < N-1 && grid[x+1][y].equals(".")) p++;
            if(y > 0 && grid[x][y-1].equals(".")) p++;
            if(y < N-1 && grid[x][y+1].equals(".")) p++;
        }
        return amt;
    }

}
