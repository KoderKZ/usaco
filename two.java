/*
ID: xfrostb1
LANG: JAVA
TASK: two
*/

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;
public class two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("248.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
        int n = Integer.parseInt(br.readLine());
        int[] list = new int[n];
        for(int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }
        int[][] dp = new int[n][n];
        int ret = 0;
        for(int len = 1; len <= n; len++) {
            for(int i = 0; i + len <= n; i++) {
                int j = i+len-1;
                dp[i][j] = -1;
                if(len == 1) {
                    dp[i][j] = list[i];
                }
                for(int k = i; k < j; k++) {
                    if(dp[i][k] == dp[k+1][j] && dp[i][k] > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
                    }
                }
                ret = Math.max(ret, dp[i][j]);
            }
        }
        pw.println(ret);
        pw.close();
    }
}