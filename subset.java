/*
 ID: xfrostb1
 LANG: JAVA
 TASK: subset
 */
import java.io.*;

public class subset {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        int n = Integer.parseInt(f.readLine());
        int sum = (int) (n * n + n) / 2;
        int sumlim = sum / 2;
        long[][] dp = new long[40][800]; // n <= 39, n(n + 1) / 2 <= 800
        for(int x = 0; x <= n; x++) dp[x][0] = 1; // There is one way to make a sum of 0 with x elements
        for(int x = 1; x <= n; x++) {
            for(int y = 1; y <= (int) (n * n + n) / 4; y++) {
                dp[x][y] += dp[x - 1][y]; // += number of ways to make sum y with x - 1 elements
                if(x <= y) dp[x][y] += dp[x - 1][y - x]; // += number of ways to make sum y - x with x - 1 elements
            }
        }

        if(sum % 2 == 0) out.println((long) dp[n][sumlim] / 2);
        else out.println(0);
        out.close();
    }
}
