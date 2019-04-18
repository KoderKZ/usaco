/*
ID: xfrostb1
LANG: JAVA
TASK: msquare
*/

import java.io.*;
import java.util.*;

public class msquare {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[][] start = new int[4][2];
        int[][] initial = new int[4][2];
        for(int i = 0; i < 4; i++) {
            start[i][0] = Integer.parseInt(st.nextToken());
            initial[i][0] = i+1;
        }
        for(int i = 3; i >= 0; i++) {
            start[i][1] = Integer.parseInt(st.nextToken());
            initial[i][0] = 8-i;
        }


    }

}
