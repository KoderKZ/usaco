/*
 ID: xfrostb1
 LANG: JAVA
 TASK: nocows
 */
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class nocows {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nocows.in"));
        PrintWriter pw = new PrintWriter(new File("nocows.out"));
        int N = in.nextInt();
        int K = in.nextInt();
        long [][] dp = new long[201][101];
        in.close();
        for(int k = 1; k <= K; k++) { // looping through all possible heights
            dp[1][k] = 1;
            for(int n = 2; n <= N; n++) { // looping through all nodes
                for(int p = 1; p <= n - 2; p++) { // looping through previous nodes
                    dp[n][k] += dp[p][k - 1] * dp[n - p - 1][k - 1];
                    dp[n][k] %= 9901;
                }

            }
        }

        pw.println((dp[N][K] - dp[N][K - 1] + 9901) % 9901);
        pw.close();

    }
//        if (n % 2 == 0){
//            out.println(0);
//            out.close();
//        }else {
//            int level = (int) Math.pow(k - 1, 2);
//
//            int num = level - ((int) Math.pow(k - 2, 2)) - 1;
//
//            level /= 2;
//            num /= 2;
//
//            int ret = 1;
//            for (int i = level; i > level - num; i--) {
//                ret *= i;
//            }
//            for (int i = 1; i <= num; i++) {
//                ret /= i;
//            }
//
//            out.println(ret);
//            out.close();
//        }


}