/*
ID: xfrostb1
LANG: JAVA
TASK: milk6
*/

import java.io.*;
import java.util.*;

public class milk6 {
    static int N, M;
    static int[][] connect;
    static int[][] clone;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk6.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        connect = new int[N+1][N+1];
        road[] roads = new road[M];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int amt = Integer.parseInt(st.nextToken());
            connect[x][y] += amt;
            roads[i] = new road(x, y, amt, i);
        }
        Arrays.sort(roads);
        clone = clone(connect);
        int flow = flow();
        out.print(flow);
        ArrayList<Integer> sol = new ArrayList<>();
        for(int i = 0; i < M; i++){
            road curr = roads[i];
            connect = clone(clone);
            connect[curr.x][curr.y] -= curr.weight;
            int nowflow = flow();
            if(nowflow+curr.weight == flow){
                flow = nowflow;
                sol.add(curr.index+1);
                clone[curr.x][curr.y] -= curr.weight;
                connect = clone(clone);
            }
            if(flow == 0) break;
        }
        Collections.sort(sol);
        out.println(" "+sol.size());
        if(sol.size() > 0){
            for(int i = 0; i < sol.size(); i++) {
                out.println(sol.get(i));
            }
        }
        out.close();
    }
    static int flow(){
        int total = 0;
        while(true){
            int[] prevnode = new int[N+1];
            Arrays.fill(prevnode, -1);
            int[] flow = new int[N+1];
            boolean[] visited = new boolean[N+1];
            flow[1] = Integer.MAX_VALUE;
            int maxflow;
            int maxloc;
            int pathcap;
            while(true) {
                maxflow = 0;
                maxloc = -1;
                for (int i = 1; i < N + 1; i++) {
                    if (flow[i] > maxflow && !visited[i]) {
                        maxflow = flow[i];
                        maxloc = i;
                    }
                }
                if (maxloc == -1) break;
                if (maxloc == N) break;
                visited[maxloc] = true;

                for (int i = 1; i < N + 1; i++) {
                    if (connect[maxloc][i] != 0) {
                        if (flow[i] < Math.min(maxflow, connect[maxloc][i])) {
                            prevnode[i] = maxloc;
                            flow[i] = Math.min(maxflow, connect[maxloc][i]);
                        }
                    }
                }

            }
            if (maxloc == -1)break;
            pathcap = flow[N];
            total += pathcap;

            int curr = N;
            while(curr != 1){
                int next = prevnode[curr];

                connect[next][curr] -= pathcap;
                connect[curr][next] += pathcap;
                curr = next;
            }
        }
        return total;
    }
    static int[][] clone(int[][] copy){
        int[][] clone = new int[copy.length][copy.length];
        for(int i = 0; i < clone.length; i++){
            for(int j = 0; j < copy[i].length; j++){
                clone[j][i] = copy[j][i];
            }
        }
        return clone;
    }
    static class road implements Comparable<road>{
        int x, y, weight, index;
        public road(int X, int Y, int Weight, int i){
            x = X;
            y = Y;
            weight = Weight;
            index = i;
        }

        @Override
        public int compareTo(road o) {
            if(o.weight == weight) return index-o.index;
            return o.weight-weight;
        }
    }

}
