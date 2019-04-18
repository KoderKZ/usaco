/*
 ID: xfrostb1
 LANG: JAVA
 TASK: numtri
 */

import java.io.*;
import java.util.StringTokenizer;

public class numtri {
    public static void main(String[] args)throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        int n = Integer.parseInt(f.readLine());
        int[][] tri = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            for(int j = 0; j <= i; j++){
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = n - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
            }
        }
        out.println(tri[0][0]);
        out.close();
    }
}
