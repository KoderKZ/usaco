/*
ID: xfrostb1
LANG: JAVA
TASK: rockers
*/

import java.io.*;
import java.util.*;

public class rockers {
    static int N, T, M;
    static int[] songs;
    static int ret = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        songs = new int[N];
        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < N; i++) songs[i] = Integer.parseInt(st.nextToken());

        int[] disks = new int[M];
        Arrays.fill(disks, T);

        dfs(disks, 0, 0, 0);
        out.println(ret);
        out.close();
    }

    static void dfs(int[] disks, int amt, int index, int dIndex) {
        boolean check = false;

        for (int i = index; i < N; i++) {
            int newdIndex = dIndex;
            while (disks[newdIndex] - songs[i] < 0) {
                newdIndex++;
                if (newdIndex >= M) {
                    break;
                }
            }
            if (newdIndex < M) {
                int[] newdisks = disks.clone();
                newdisks[newdIndex] -= songs[i];
                dfs(newdisks, amt + 1, i + 1, newdIndex);
                check = true;
            }
        }

        if (!check) ret = Math.max(ret, amt);
    }
}
