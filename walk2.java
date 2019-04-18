/*
ID: xfrostb1
LANG: JAVA
TASK: walk2
*/

import java.io.*;
import java.util.*;

public class walk2 {
    static long X = 2019201913;
    static long Y = 2019201949;
    static long MOD = 2019201997;
    static boolean[] visited;
    static ArrayList<Integer>[] connect;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("walk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
//        long[][] connect = new long[N][N];
        PriorityQueue<edge> pq = new PriorityQueue<>();
        connect = new ArrayList[N];
        for(int i = 0; i < N; i++) connect[i] = new ArrayList();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long weight = (((i + 1) * X) + ((j + 1) * Y)) % MOD;
                pq.add(new edge(i, j, weight));
                connect[i].add(j);
                connect[j].add(i);
            }
        }

        while(true){
            edge node = pq.poll();
            connect[node.x].remove((Object)node.y);
            connect[node.y].remove((Object)node.x);

            visited  = new boolean[N];
            int amt = 0;
            for(int i = 0; i < N; i++){
                if(!visited[i]) {
                    dfs(i);
                    amt++;
                }
            }


            if(amt==M){
                out.println(node.weight);
                break;
            }
        }

        out.close();

    }

    static void dfs(int source){
        visited[source] = true;
        for(int i : connect[source]){
            if(!visited[i]) dfs(i);
        }
    }
    static class edge implements Comparable<edge>{
        int x, y;
        long weight;
        public edge(int X, int Y, long w){
            weight = w;
            x = X;
            y = Y;
        }

        @Override
        public int compareTo(edge o) {
            return (int)-(weight-o.weight);
        }
    }
}