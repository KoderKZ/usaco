/*
ID: xfrostb1
LANG: JAVA
TASK: range
*/

import java.io.*;
import java.util.*;

public class range {
    static int N;
    static int[][] field;
    static int[] amt;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("range.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
        N = Integer.parseInt(f.readLine());
        field = new int[N][N];
        for(int i = 0; i < N; i++){
            String s = f.readLine();
            for(int j = 0; j < N; j++){
                field[j][i] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
        amt = new int[N];
        visited = new boolean[N][N][N];

        for(int i = N; i >= 2; i--){
            check(i);
        }
        for(int i = 0; i < N; i++){
            if(amt[i] != 0) out.println((i+1)+" "+amt[i]);
        }
        out.close();

    }
    static void check(int size){
        for(int y = 0; y <= N-size; y++){
            for(int x = 0; x <= N-size; x++){
                if(!visited[x][y][size-1]) {
                    if (valid(x, y, size)) {
                        visited[x][y][size-1] = true;
                        amt[size-1]++;
                        for (int i = size - 1; i >= 2; i--) {
                            amt[i-1] += Math.pow((size - i) + 1, 2);
                            fill(x, y, i, size-i+1);
                        }
                    }
                }
            }
        }
    }

    static void fill(int x, int y, int size, int inc){
        for(int i = y; i < y+inc; i++){
            for(int j = x; j < x+inc; j++) {
                if(visited[j][i][size-1]) amt[size-1]--;
                visited[j][i][size-1] = true;
            }
        }
    }

    static boolean valid(int x, int y, int size){
        for(int i = y; i < y+size; i++){
            for(int j = x; j < x+size; j++){
                if(field[j][i] == 0) return false;
            }
        }
        return true;
    }
}
