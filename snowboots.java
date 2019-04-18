import java.io.*;
import java.util.*;

public class snowboots {
    static int[] path, depth, steps;
    static boolean[][] visited;
    static int n, b;
    static int best = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        path = new int[n];
        for(int i = 0; i < n; i++){
            path[i] = Integer.parseInt(st.nextToken());
        }
        depth = new int[b];
        steps = new int[b];
        for(int i = 0; i < b; i++){
            st = new StringTokenizer(f.readLine());
            depth[i] = Integer.parseInt(st.nextToken());
            steps[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[n][b];
        dfs(0,0);
        out.println(best);
        out.close();
    }
    static void dfs(int boot, int step) {
        if (!visited[step][boot]) {
            visited[step][boot] = true;

            if (step + steps[boot] >= n) {
                best = Math.min(best, boot);
                return;
            }

            for (int i = 1; i <= steps[boot]; i++) {
                if (path[step + i] <= depth[boot]) {
                    dfs(boot, step + i);
                }
            }

            for (int j = boot + 1; j < b; j++) {
                if (depth[j] >= path[step]) {
                    dfs(j, step);
                }
            }

        }
    }
}
