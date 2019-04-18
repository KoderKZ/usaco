/*
ID: xfrostb1
LANG: JAVA
TASK: talent
*/

import java.io.*;
import java.util.*;

public class talent {
    static int N, K;
    static int[] w, t;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("talent.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        w = new int[N];
        t = new int[N];
        dp = new long[K+1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(f.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            t[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0;
        int high = Integer.MAX_VALUE;
        while (high > low + 1) {
            int mid = (low + high) / 2;
            if (viable(mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        out.println(low);
        out.close();

    }
    static boolean viable(int y){
        for (int i = 0; i <= K; i++) {
            dp[i] = -Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int j = 0; j < N; j++) {
            long value = 1000*(long)t[j] - y*(long)w[j];
            int inc = w[j];
            for (int k = K; k >= 0; k--) {
                int k1 = Math.min(K, k + inc);
                if (dp[k] != -Integer.MAX_VALUE) {
                    if (dp[k1] < dp[k] + value) {
                        dp[k1] = dp[k] + value;
                    }
                }
            }
        }

        return dp[K] >= 0;
    }
}
