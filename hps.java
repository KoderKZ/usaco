/*
ID: xfrostb1
LANG: JAVA
TASK: hps
*/

import java.io.*;
import java.util.*;

public class hps {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("hps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] plays = new int[N];
        for(int i = 0; i < N; i++){
            String x = f.readLine();
            if(x.equals("H")){
                plays[i] = 0;
            }
            if(x.equals("P")){
                plays[i] = 1;
            }
            if(x.equals("S")){
                plays[i] = 2;
            }

        }
        int[][][] dp = new int[N+1][M+1][3];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int state = 0; state < 3; state++) {
                    if (i == 0) {
                        dp[i][j][state] = 0;
                    } else {
                        if (j == 0) {
                            dp[i][j][state] = dp[i-1][j][state] + (plays[i-1] == state ? 1 : 0);
                        } else {
                            int ostate1 = (state + 1) % 3;
                            int ostate2 = (state + 2) % 3;
                            dp[i][j][state] = Math.max(Math.max(dp[i-1][j][state], dp[i-1][j-1][ostate1]), dp[i-1][j-1][ostate2]) + (plays[i-1] == state ? 1 : 0);
                        }
                    }
                }
            }
        }

        out.println(Math.max(Math.max(dp[N][M][0], dp[N][M][1]), dp[N][M][2]));
        out.close();
    }
}
