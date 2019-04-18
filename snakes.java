/*
ID: xfrostb1
LANG: JAVA
TASK: snakes
*/

import java.io.*;
import java.util.*;

public class snakes {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("snakes.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] groups = new int[N];
        st = new StringTokenizer(f.readLine());
        int[] sum = new int[N];
        int[] maxa = new int[N];
        for(int i = 0; i < N; i++){
            groups[i] = Integer.parseInt(st.nextToken());
        }
        sum[0] = groups[0];
        maxa[0] = groups[0];
        for(int i = 1; i < N; i++) {
            sum[i]+=sum[i-1]+groups[i];
            maxa[i] = Math.max(maxa[i-1], groups[i]);
        }
        //amt groups, times changed, last time changed
        int[][] dp = new int[N][M+1];
        dp[0][0] = 0;


        int ret = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++){
            for(int j = 0; j <= M; j++){
                if(j==0) {
                    dp[i][j] = (maxa[i]*(i+1))-sum[i];
                }else {

                    int max = groups[i];
                    for (int k = i; k > 0 && i - k >= 0; k--) {
                        max = Math.max(groups[k], max);
                        if(dp[i][j]!=0) {
                            dp[i][j] = Math.min(dp[i][j],Math.min(dp[i][j - 1], dp[k-1][j - 1] + ((max * (i - k + 1) - (sum[i] - sum[k-1])))));
                        }else{
                            dp[i][j] = Math.min(dp[i][j - 1], dp[k-1][j - 1] + ((max * (i-k+1) - (sum[i] - sum[k-1]))));
                        }
                        if (i == N - 1) ret = Math.min(ret, dp[i][j]);
                    }
                }
            }
        }
        out.println(ret);
        out.close();
    }
}
//
//    int[][][] dp = new int[N][M][N];
//        for(int i = 0; i < N; i++){
//        dp[0][0][i] = 0;
//        }
//
//        int ret = Integer.MAX_VALUE;
//        for(int i = 1; i < N; i++){
//        for(int j = 0; j < M; j++){
//        if(j==0) {
//        for(int k = 0; k < N; k++) dp[i][j][k] = (maxa[i]*(i+1))-sum[i];
//        }else {
//
//        int max = groups[i];
//        for (int k = i; k >= 0 && i - k >= 0; k++) {
//        max = Math.max(groups[k], max);
//        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][j - 1][i - k] + ((max * (i - k) - (sum[i] - sum[i - k]))));
//        if (i == N - 1) ret = Math.min(ret, dp[i][j][k]);
//        }
//        }
//        }
//        }
//        out.println(ret);
//        out.close();