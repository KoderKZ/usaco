/*
ID: xfrostb1
LANG: JAVA
TASK: tour
*/

import java.io.*;
import java.util.*;

public class tour {
    static boolean[][] connections;
    static int max = 1, N, M;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("tour.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tour.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        TreeMap<String, Integer> cities = new TreeMap<>();
        for(int i = 0; i < N; i++){
            cities.put(f.readLine(), i);
        }
        connections = new boolean[N][N];
        visited = new boolean[N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(f.readLine());
            int x = cities.get(st.nextToken());
            int y = cities.get(st.nextToken());
            connections[x][y] = connections[y][x] = true;
        }
        int i,j,k;
        int[][] dp = new int[N+1][N+1];
        dp[0][0]=1;
        for (i = 0; i < N; i++) {
            for (j = i + 1; j < N; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                for (k = 0; k < j; k++) {
                    if (connections[k][j] && dp[i][k] > 0 && dp[i][k] > dp[i][j])
                        dp[i][j] = dp[i][k];
                }
                dp[j][i] = ++dp[i][j];
            }
        }
        for (i = 1; i < N-1; i++) {
            if (connections[i][N-1] && dp[i][N-1] > max) max = dp[i][N-1];
        }
        out.println(max);
        out.close();

//        dfs1(0, 0);
//        out.println(max);
//        out.close();
    }
//    static void dfs1(int node, int count){
//        if(node == N-1){
//            int second = dfs2(N-1, 0);
//            if(second!=-1) {
//                max = Math.max(count+second, max);
//            }
//            return;
//        }
//        for(int i = node+1; i < N; i++){
//            if(connections[node][i]&&!visited[i]&&i!=0){
//                visited[i] = true;
//                dfs1(i, count+1);
//                visited[i] = false;
//            }
//        }
//    }
//    static int dfs2(int node, int count){
//        if(node == 0){
//            return count;
//        }
//        for(int i = node-1; i >= 0; i--){
//            if(connections[node][i]&&!visited[i]&&i!=N-1){
//                visited[i] = true;
//                int ret = dfs2(i, count+1);
//                if(ret != -1){
//                    visited[i] = false;
//                    return ret;
//                }else {
//                    visited[i] = false;
//                }
//            }
//        }
//        return -1;
//    }
}
