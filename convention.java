import java.io.*;
import java.util.*;

public class convention {
    static long[] max;
    static long[] cows;
    static boolean[] visited;
    static long M, c;
    static int n, min;
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("convention.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        n = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        cows = new long[n];
        visited = new boolean[n+1];
        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < n; i++) cows[i] = Integer.parseInt(st.nextToken());

        if(M >= n) {
            out.println(0);
            out.close();
        }else{
            min = 1;
            min = Math.max((int) (n - ((M - 1) * c)), min);

            Arrays.sort(cows);

            max = new long[n + 1];

            search(0, 0, 0);

            int m = Integer.MAX_VALUE;

            out.println(max[n]);
            out.close();
        }


    }
    static void search(int x, int prev, int m){
        if(!visited[x]) {
            if (m >= M) return;
            for (int i = min; i < c + 1 && x+i <= n; i++) {
                max[x + i] = Math.max(max[x+i], Math.max(max[x], cows[x + i - 1] - cows[x]));
            }

            if (x == n) {
                out.println(max[n]);
                System.exit(0);
                return;
            }

            if(m == M) return;
            if(m == M-1) search(n, x, m+1);
            else {
                for (int i = min; i < c + 1 && x + i < n; i++) {
                    search(x + i, x, m + 1);
                }
            }
        }
    }
}
