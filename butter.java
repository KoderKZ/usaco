/*
ID: xfrostb1
LANG: JAVA
TASK: butter
*/

import java.io.*;
import java.util.*;

public class butter {

    private int[][] map;
    private long[][] shortest;

    private long[] spfa(int source) {
        ArrayList<Integer> toCheck = new ArrayList<Integer>();
        toCheck.add(source);
        long[] dist = new long[map.length];
        for (int i = 0; i < dist.length; i++) {
            if (i < source && shortest[source][i] != Long.MAX_VALUE) {
                // to queue v at least once
                dist[i] = shortest[source][i] + 1;
            } else {
                dist[i] = Long.MAX_VALUE;
            }
        }
        dist[source] = 0;

        while (toCheck.size() > 0) {
            int u = toCheck.remove(0);
            for (int v : neighbors.get(u)) {
                if (u == v) continue;
                if (map[u][v] == 0) continue;
                long tmp = dist[v];
                long d = Math.min(dist[v], dist[u] + map[u][v]);
                dist[v] = d;
                if (tmp != dist[v] && !toCheck.contains(v)) {
                    toCheck.add(v);
                }
            }
        }
        return dist;
    }


    // cache it!
    private HashMap<Integer, ArrayList<Integer>> neighbors;
    private long minCost = Long.MAX_VALUE;

    public long solve(int[] cows, int[][] map) {
        this.map = map;

        int graphSize = map.length - 1;
        shortest = new long[graphSize + 1][graphSize + 1];

        neighbors = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 1; i <= graphSize; i++) {
            neighbors.put(i, new ArrayList<Integer>());
            for (int j = 1; j <= graphSize; j++) {
                shortest[i][j] = Long.MAX_VALUE;
                if (map[i][j] > 0) {
                    neighbors.get(i).add(j);
                }
            }
        }

        for (int i : cows) {
            long[] dist = spfa(i);
            for (int j = 1; j <= graphSize; j++) {
                shortest[i][j] = shortest[j][i] = dist[j];
            }
        }

        for (int p = 1; p <= graphSize; p++) {
            long cost = 0;
            for (int c : cows) {
                if (shortest[p][c] == Long.MAX_VALUE) {
                    cost = minCost;
                    break;
                }
                cost += shortest[p][c];
            }
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public static void main(String[] args) throws IOException {
        String problemName = "butter";
        BufferedReader f = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(f.readLine());

        int n = Integer.parseInt(st.nextToken());
        int points = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());

        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(f.readLine());
        }
        int[][] map = new int[points + 1][points + 1];
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(f.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[from][to] = map[to][from] = Integer.parseInt(st.nextToken());
        }
        long res = (new butter()).solve(cows, map);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        out.println(res);
        out.close(); // close the output file
        System.exit(0); // don't omit this!
    }

}