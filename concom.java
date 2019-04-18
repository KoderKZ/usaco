/*
 ID: xfrostb1
 LANG: JAVA
 TASK: concom
 */
import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class concom {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("concom.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        int n = Integer.parseInt(f.readLine());
        int[][] dp = new int[101][101];
        boolean[][] visited = new boolean[101][101];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        boolean cont = true;

        while(cont) {
            cont = false;
            for(int i = 1; i < 101; i++) { // going through all companies
                for(int j = 1; j < 101; j++) { // does company i control company j?
                    if(! visited[i][j] && i != j && dp[i][j] > 50) {
                        visited[i][j] = true;
                        cont = true;
                        for(int k = 1; k < 101; k++) {
                            if(i != k && j != k) dp[i][k] += dp[j][k];
                        }
                    }
                }
            }
        }
        for(int i = 1; i < 101; i++) {
            for(int j = 1; j < 101; j++) {
                if(Math.abs(dp[i][j]) > 50 && i != j) out.println(i + " " + j);
            }
        }
        out.close();

    }
}