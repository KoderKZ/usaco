/*
ID: xfrostb1
LANG: JAVA
TASK: teamwork
*/

import java.io.*;
import java.util.*;

public class teamwork {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cows = new int[N];
        for(int i = 0; i < N; i++) cows[i] = Integer.parseInt(f.readLine());


        int[] dp = new int[N];
        dp[0] = cows[0];
        for(int i = 1; i < N; i++){
            int max = cows[i];
            for(int j=i;j>=0 && i+1-j <= K; j--){
                max = Math.max(max, cows[j]);
                if(j==0) dp[i] = Math.max(dp[i],max*(i+1-j));
                else dp[i] = Math.max(dp[i],dp[j-1] + max*(i+1-j));
            }
        }
        out.println(dp[N-1]);
        out.close();
    }
}
