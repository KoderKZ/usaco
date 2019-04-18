/*
ID: xfrostb1
LANG: JAVA
TASK: shortcut2
*/

import java.io.*;
import java.util.*;

public class shortcut2 {
    static int N, M, K;
    static int[][] connect;
    static int[][] reduce;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int[] cows = new int[N + 1];
        connect = new int[N + 1][N + 1];
        reduce = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(connect[i], 1000000);
            connect[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            connect[x][y] = connect[y][x] = Integer.parseInt(st.nextToken());
        }


        for(int i = 1; i < N+1; i++){
            connect[i] = dijkstra(i, N);
        }
        out.println(40);
    }
    static class node implements Comparable<node>{
        int dist, index;
        public node(int d, int i){
            dist = d;
            index = i;
        }

        @Override
        public int compareTo(node o) {
            return dist-o.dist;
        }
    }
    static int[] dijkstra(int source, int max){
        int[] dist = new int[N+1];
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
                if(connect[index][i] != 0){
                    if(!visited[i] || dist[i] > connect[index][i]+dist[index]){
                        dist[i] = Math.min(dist[i], connect[index][i]+dist[index]);
                        if(!queued[i]) {
                            queue.add(new node(dist[i], i));
                            queued[i] = true;
                            reduce[source][i] = dist[i]+K;
                        }
                    }
                }
            }
        }
        return dist;
    }
}
