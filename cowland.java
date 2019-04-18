/*
ID: xfrostb1
LANG: JAVA
TASK: cowland
*/

import java.io.*;
import java.util.*;

public class cowland {
    static int[] conn;
    static boolean[][] right;
    static boolean[] visited;
    static int N, M;
    static int[] enjoyment;
    static int[] parent;
    static int[] change;
    static boolean[] changed;
//    static boolean[][] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowland.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        right = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        enjoyment = new int[N + 1];
        conn = new int[N+1];
        parent = new int[N+1];
        parent[1] = -1;
        change = new int[N+1];
        changed = new boolean[N+1];
        st = new StringTokenizer(f.readLine());
        for (int i = 1; i < N + 1; i++) {
            enjoyment[i] = Integer.parseInt(st.nextToken());
            change[i] = enjoyment[i];
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            right[x][y] = right[y][x] = true;
        }
        dfs(1, 1, enjoyment[1]);
        String ret = "";
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            int c = 0;
            if (Integer.parseInt(st.nextToken()) == 2) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int par = x;
                int par2 = y;
                boolean a = false;
                boolean b = false;
                int lca = -1;
                boolean[] visited = new boolean[N+1];
                while (par != -1) {
                    visited[par] = true;
                    if(changed[par]) {
                        c ^= change[par];
                    }
                    if (parent[par] == y) {
                        a = true;
                    }
                    par = parent[par];

                }
                par = y;
                while (par != -1) {
                    if(lca == -1 && visited[par]) {
                        lca = par;
                    }
                    if(changed[par]) {
                        c ^= change[par];
                    }
                    if (parent[par] == x) {
                        b = true;
                    }
                    par = parent[par];
                }
//                if (x == 1 || y == 1) {
//                    ret = ret + (conn[y] ^ conn[x] ^ enjoyment[1]^c) + "\n";
//                } else if (a) {
//                    ret = ret + (conn[y] ^ conn[x] ^ enjoyment[y]^c) + "\n";
//                } else if (b) {
//                    ret = ret + (conn[y] ^ conn[x] ^ enjoyment[x]^c) + "\n";
//                } else {
                    ret = ret + (conn[y] ^ conn[x] ^ enjoyment[lca]^c) + "\n";
//                }
            } else {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                change[x]^=y;
                enjoyment[x] = y;
                changed[x] = true;
//                break;
            }
        }
        out.print(ret);
        out.close();

    }
    static void dfs(int root, int node, int amt){
        if(!visited[node]){
            conn[node] = amt;
            visited[node] = true;
            for(int i = 1; i < N+1; i++){
                if(right[node][i] && !visited[i]){
                    parent[i] = node;
                    dfs(root, i, amt^enjoyment[i]);
                }
            }
        }
    }
//    static void dfs(int curr, int old, int n) {
//
//        if (!visited[curr]) {
//            visited[curr] = true;
//            for (int i = 1; i < N + 1; i++) {
//                if (right[curr][i]) {
//                    conn[curr][i]^=old;
//                    conn[curr][i]^=n;
//                    dfs(i, old, n);
//                }
//            }
//        }
//    }
}


///*
//ID: xfrostb1
//LANG: JAVA
//TASK: cowland
//*/
//
//import java.io.*;
//import java.util.*;
//
//public class cowland {
//    //    static int[][] conn;
//    static boolean[][] right;
//    static boolean[] visited;
//    static int N, M;
//    static int[] enjoyment;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("cowland.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
////        conn = new int[N+1][N+1];
//        right = new boolean[N + 1][N + 1];
//        visited = new boolean[N + 1];
//        enjoyment = new int[N + 1];
//        st = new StringTokenizer(f.readLine());
//        for (int i = 1; i < N + 1; i++) {
//            enjoyment[i] = Integer.parseInt(st.nextToken());
//        }
//
//        for (int i = 0; i < N - 1; i++) {
//            st = new StringTokenizer(f.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
////            conn[x][y] = conn[y][x] = enjoyment[x]^enjoyment[y];
//            right[x][y] = right[y][x] = true;
//        }
//
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(f.readLine());
//            if (Integer.parseInt(st.nextToken()) == 2) {
//                visited = new boolean[N + 1];
//                int x = Integer.parseInt(st.nextToken());
//                int y = Integer.parseInt(st.nextToken());
//                out.println(dfs(x, y, enjoyment[x]));
//            } else {
//                int x = Integer.parseInt(st.nextToken());
//                int y = Integer.parseInt(st.nextToken());
//                enjoyment[x] = y;
//            }
//        }
//        out.close();
//
//    }
//
//    static int dfs(int curr, int end, int amt) {
//        if (curr == end) {
//            return amt;
//        }
//        if (!visited[curr]) {
//            visited[curr] = true;
//            for (int i = 1; i < N + 1; i++) {
//                if (right[curr][i]) {
//                    int x = dfs(i, end, amt ^ enjoyment[i]);
//                    if(x != -1) return x;
//                }
//            }
//        }
//        return -1;
//    }
//}
