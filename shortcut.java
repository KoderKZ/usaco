/*
ID: xfrostb1
LANG: JAVA
TASK: shortcut
*/

import java.io.*;
import java.util.*;

public class shortcut {
    static int N, M, K;
    static long[][] connect;
    static int[] parent;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int[] cows = new int[N + 1];
        connect = new long[N + 1][N + 1];
        parent = new int[N+1];
        for (int i = 1; i < N + 1; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
            connect[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            connect[x][y] = connect[y][x] = Integer.parseInt(st.nextToken());
        }

        dijkstra(1, N);

        int[] visited = new int[N+1];
        parent[1] = -1;
        for(int k = 1; k <= N; k++){
            int i = k;
            while(i != -1){
                visited[i] += cows[k];
                i = parent[i];
            }
        }
        long answer = 0;
        for(int k = 1; k <= N; k++){
            answer = Math.max(answer,visited[k]*(dist[k]-K));
        }



        out.println(answer);
        out.close();
    }
    static class node implements Comparable<node>{
        long dist;
        int index;
        public node(long d, int i){
            dist = d;
            index = i;
        }

        @Override
        public int compareTo(node o) {
            if(dist < o.dist) return -1;
            if(dist != o.dist) return 1;
            return 0;
        }
    }
    static void dijkstra(int source, int max){
        dist = new long[N+1];
        boolean[] queued = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        Queue<node> queue = new PriorityQueue<>();
        queue.add(new node(0, source));
        boolean[] visited = new boolean[N+1];
        while(queue.size() > 0){
            node next = queue.poll();
            int index = next.index;
            visited[index] = true;
            queued[index] = false;
            for(int i = 1; i < max+1; i++){
                if(connect[index][i] != 0 && !visited[i]){
                    if(!visited[i] || dist[i] > connect[index][i]+dist[index]){
                        dist[i] = Math.min(dist[i], connect[index][i]+dist[index]);
                        if(!queued[i]) {
                            queue.add(new node(dist[i], i));
                            queued[i] = true;
                            parent[i] = index;
                        }
                    }
                }
            }
        }
    }


//    static int[][] next;
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("shortcut.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//        st = new StringTokenizer(f.readLine());
//        int[] cows = new int[N+1];
//        int[][] connect = new int[N+1][N+1];
//        next = new int[N+1][N+1];
//        for(int i = 1; i < N+1; i++) {
//            cows[i] = Integer.parseInt(st.nextToken());
//            Arrays.fill(connect[i], 1000000);
//            connect[i][i] = 0;
//        }
//        for(int i = 1; i < N+1; i++){
//            for(int j = 1; j < N+1; j++) next[i][j] = j;
//        }
//
//
//        for(int i = 0; i < M; i++){
//            st = new StringTokenizer(f.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
//            connect[x][y] = connect[y][x] = Integer.parseInt(st.nextToken());
//        }
//
//        for (int i = 1; i < N+1; i++) {
//            for (int k = 1; k < N+1; k++) {
//                for (int j = 0; j < N+1; j++) {
//                    if (connect[i][k] + connect[k][j] < connect[i][j]) {
//                        connect[i][j] = connect[i][k] + connect[k][j];
//                        next[i][j] = next[i][k];
//                    }
//                }
//            }
//        }
////        ArrayList[] paths = new ArrayList[N+1];
////        for(int i = 1; i < N+1; i++){
////            paths[i] = path(i, 1);
////        }
////        int ret = 0;
////        for(int i = 2; i < N+1; i++){
////            int total = 0;
////            for(int j = 2; j < N+1; j++){
//////                System.out.println(connect[1][j]-(K+connect[j][i])+" "+paths[j].contains(i));
////                if(paths[j].contains(i) && connect[1][j]-(K+connect[j][i]) > 0) {
////                    total += (connect[1][j]-(K+connect[j][i]))*cows[j];
////                }
////            }
////            ret = Math.max(total, ret);
////        }
//        out.println(40);
//        out.close();
//    }
//
//    static ArrayList<Integer> path(int u, int v){
//        ArrayList<Integer> ret = new ArrayList<>();
//        ret.add(u);
//        while(u != v){
//            u = next[u][v];
//            ret.add(u);
//        }
//        return ret;
//    }
//    procedure Path(u, v)
//   if next[u][v] = null then
//       return []
//    path = [u]
//            while u ≠ v
//    u ← next[u][v]
//            path.append(u)
//            return path
}
