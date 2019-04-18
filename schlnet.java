/*
ID: xfrostb1
LANG: JAVA
TASK: schlnet
*/

import java.io.*;
import java.util.*;

public class schlnet {
    static int g[][] = new int[101][101];
    static int n;
    static int color[] = new int[101];
    static int parent[] = new int[101];
    static int sons[] = new int[101];
    static int g2[][] = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("schlnet.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));
        n = Integer.valueOf(f.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            while (st.hasMoreTokens()) {
                int tmp = Integer.valueOf(st.nextToken());
                if (tmp != 0) {
                    g[i + 1][tmp] = 1;
                    g2[i + 1][tmp] = 1;
                    g2[tmp][i + 1] = 1;
                    sons[i + 1]++;
                    parent[tmp]++;
                }
            }
        }
        int c = 1;
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0 && parent[i] == 0) {
                dfs(i, c);
                c++;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                dfs2(i, c);
                c++;
            }
        }
        c--;
        out.println(c);
        System.out.println(c);
        int count = 0;
        int tmp2 = 0;
        for (int i = 1; i <= n; i++) {
            if (sons[i] == 0) tmp2++;
        }
        count = c == 1 ? 0 : c;
        if (tmp2 > count) count = tmp2;
        out.println(count);
        System.out.println(count);
        out.close();
    }

    static void dfs(int a, int b) {
        color[a] = b;
        for (int i = 1; i < 101; i++) {
            if (g[a][i] == 1 && color[i] == 0) {
                dfs(i, b);
            }
        }
    }

    static void dfs2(int a, int b) {
        color[a] = b;
        for (int i = 1; i < 101; i++) {
            if (g2[a][i] == 1 && color[i] == 0) {
                dfs(i, b);
            }
        }
    }
}

