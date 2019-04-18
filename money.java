/*
 ID: xfrostb1
 LANG: JAVA
 TASK: money
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class money {
    static ArrayList<Integer[]> list = new ArrayList<>();
    static long[] coins;
    static int ret = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("money.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        coins = new long[n];
        for(int i = 0; i < n; i++){
            if (st.hasMoreTokens()) {
                coins[i] = Integer.parseInt(st.nextToken());
            }else{
                st = new StringTokenizer(f.readLine());
                coins[i] = Integer.parseInt(st.nextToken());
            }
        }
        long[] dp = new long[k+1];
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(long j = coins[i]; j <= k; j++)
                dp[(int) j] += dp[(int) (j - coins[i])];
        }

        out.println(dp[k]);
        out.close();
    }

}