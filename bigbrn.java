/*
ID: xfrostb1
LANG: JAVA
TASK: bigbrn
*/

import java.io.*;
import java.util.*;

public class bigbrn {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("bigbrn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] graph = new boolean[N][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(f.readLine());
            graph[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }

        int[][] dp = new int[N + 1][N + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!graph[i-1][j-1]){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        out.println(maxsqlen);
        out.close();
    }
}
