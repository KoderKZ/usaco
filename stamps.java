/*
ID: xfrostb1
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.*;

public class stamps {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] stamps = new int[m];
        String line = f.readLine();
        int counter = 0;
        while (line != null){
            st = new StringTokenizer(line);
            while(st.hasMoreTokens()){
                stamps[counter++] = Integer.parseInt(st.nextToken());
            }

            line = f.readLine();
        }
        out.println(solve(n, stamps));
        out.close();



    }
    public static int solve(int maxStamps, int[] stamps) {
        Arrays.sort(stamps);
        int maxValue = stamps[stamps.length - 1] * maxStamps;

        int[] dp = new int[maxValue + 1];
        for (int i = 1; i < dp.length;i++) {
            dp[i] = maxStamps + 1;
        }

        for (int i = 1; i <= maxValue; i++) {
            for (int v : stamps) {
                if (i - v < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - v] + 1);
            }
            if (dp[i] > maxStamps) return i - 1;
        }

        return maxValue;
    }
}
