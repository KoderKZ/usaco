/*
ID: xfrostb1
LANG: JAVA
TASK: walk
*/

import java.io.*;
import java.util.*;

public class walk {
    static long X = 2019201913;
    static long Y = 2019201949;
    static long MOD = 2019201997;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("walk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<edge> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                long weight = (((i+1)*X)+((j+1)*Y))%MOD;
                pq.add(new edge(i, j, weight));
            }
        }

        dset set = new dset(N);
        while(set.numTrees>M){
            edge node = pq.poll();
            set.union(node.x, node.y);
        }

        out.println(pq.poll().weight);
        out.close();

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
            return (int)(weight-o.weight);
        }
    }
    static class pair {
        public int parent;
        public int height;

        public pair(int a, int b) {
            parent = a;
            height = b;
        }
    }

    // Basic Disjoint Set without path compression.
    static class dset {

        private pair[] parents;
        public int numTrees;

        public dset(int n) {
            parents = new pair[n];
            for (int i=0; i<n; i++)
                parents[i] = new pair(i,0);
            numTrees = n;
        }

        public int find(int child) {
            while (parents[child].parent != child)
                child = parents[child].parent;
            return child;
        }

        public boolean union(int c1, int c2) {
            int root1 = find(c1);
            int root2 = find(c2);

            // Nothing to union.
            if (root1 == root2)
                return false;

            // Root 1 stays parent.
            if (parents[root1].height > parents[root2].height) {
                parents[root2].parent = root1;
            }

            // Tie case get assigned to root 1 also.
            else if (parents[root1].height == parents[root2].height) {
                parents[root2].parent = root1;
                parents[root1].height++;
            }

            // Must use root 2 here.
            else {
                parents[root1].parent = root2;
            }

            numTrees--;
            return true;
        }
    }
}
