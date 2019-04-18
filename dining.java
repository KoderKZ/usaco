///*
//ID: xfrostb1
//LANG: JAVA
//TASK: dining
//*/
//
//import java.io.*;
//import java.util.*;
//
//public class dining {
//    static int N, M, K;
//    static int[][] path;
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("dining.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//        path = new int[N+1][N+1];
//        for(int i = 0; i < M; i++){
//            st = new StringTokenizer(f.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
//            path[x][y] = path[y][x] = Integer.parseInt(st.nextToken());
//        }
//
//        int[] food = new int[N+1];
//
//        for(int i = 0; i < K; i++) {
//            st = new StringTokenizer(f.readLine());
//            food[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
//        }
//
//        int[] first = dijkstra(N, N-1);
//
//
////        for(int i = 1; i < N; i++){
////            path[N][i] = 0;
////        }
//        for(int i = 1; i < N; i++){
//            if(food[i] > 0){
//                path[0][i] = first[i]-food[i];
////                path[i][0] = first[i]-food[i];
//            }
//        }
//        int[] second = dijkstra(0, N);
//        for(int i = 1; i < N; i++){
//            out.println(second[i] <= first[i]?1:0);
//        }
//        out.close();
//
//    }
//    static int[] dijkstra(int source, int max){
//        int[] dist = new int[N+1];
//        boolean[] queued = new boolean[N+1];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//        dist[source] = 0;
//        Queue<node> queue = new PriorityQueue<>();
//        queue.add(new node(0, source));
//        boolean[] visited = new boolean[N+1];
//        while(queue.size() > 0){
//            node next = queue.poll();
//            int index = next.index;
//            visited[index] = true;
//            queued[index] = false;
//            for(int i = 1; i < max+1; i++){
//                if(path[index][i] != 0){
//                    if(!visited[i] || dist[i] > path[index][i]+dist[index]){
//                        dist[i] = Math.min(dist[i], path[index][i]+dist[index]);
//                        if(!queued[i]) {
//                            queue.add(new node(dist[i], i));
//                            queued[i] = true;
//                        }
//                    }
//                }
//            }
//        }
//        return dist;
//    }
//    static class node implements Comparable<node>{
//        int dist, index;
//        public node(int d, int i){
//            dist = d;
//            index = i;
//        }
//
//        @Override
//        public int compareTo(node o) {
//            return dist-o.dist;
//        }
//    }
//}
