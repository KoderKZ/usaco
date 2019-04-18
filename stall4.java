/*
ID: xfrostb1
LANG: JAVA
TASK: stall4
*/

//import java.io.*;
//import java.util.*;
//
//public class stall4 {
//    static int N, M, ret = 0;
//    static int[][] pref;
//    static int[] count;
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("stall4.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        int[][] pref = new int[N][];
//        count = new int[N];
//        for(int i = 0; i < N; i++){
//            st = new StringTokenizer(f.readLine());
//            int n = Integer.parseInt(st.nextToken());
//            pref[i] = new int[n];
//            count[i] = n;
//            for(int j = 0; j < n; j++) pref[i][j] = Integer.parseInt(st.nextToken());
//        }
//
//        boolean[] stalls = new boolean[M+1];
//
//        dfs(0, stalls, 0, pref);
//        out.println(ret);
//        out.close();
//    }
//
//    static void dfs(int num, boolean[] stalls, int amt, int[][] pref){
//        boolean check = false;
//        if(num == N) {
//            ret = Math.max(amt, ret);
//            return;
//        }
//        for(int i = 0; i < count[num]; i++){
//            if(!stalls[pref[num][i]]){
//                stalls[pref[num][i]] = true;
//                dfs(num+1, stalls, amt+1, pref);
//                stalls[pref[num][i]] = false;
//                check = true;
//            }
//        }
//
//        if(check == false){
//            dfs(num+1, stalls, amt, pref);
//        }
//    }
//
//}

import java.io.*;
import java.util.*;

public class stall4 {
    static int N, M, ret = 0;
    static int[][] pref;


    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int sink = N+M+1;
        pref = new int[sink+1][sink+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            int n = Integer.parseInt(st.nextToken());

            for (int j = 0; j < n; j++) pref[i+1][N+Integer.parseInt(st.nextToken())] = 1;

            pref[0][i+1] = 1;
        }
        for(int i = 1; i < M+1; i++){
            pref[N+i][sink] = 1;
        }

        int total = 0;


        while(true){
            int[] prevnode = new int[sink+1];
            Arrays.fill(prevnode, -1);
            int[] flow = new int[sink+1];
            boolean[] visited = new boolean[sink+1];
            flow[0] = Integer.MAX_VALUE;
            int maxflow = 0;
            int maxloc = -1;
            int pathcap;
            while(true) {
                maxflow = 0;
                maxloc = -1;
                for (int i = 0; i < sink+1; i++) {
                    if (flow[i] > maxflow && !visited[i]) {
                        maxflow = flow[i];
                        maxloc = i;
                    }
                }
                if (maxloc == -1) break;
                if (maxloc == sink) break;
                visited[maxloc] = true;

                for (int i = 0; i < sink + 1; i++) {
                    if (pref[maxloc][i] != 0) {
                        if (flow[i] < Math.min(maxflow, pref[maxloc][i])) {
                            prevnode[i] = maxloc;
                            flow[i] = Math.min(maxflow, pref[maxloc][i]);
                        }
                    }
                }

            }
            if (maxloc == -1)break;
            pathcap = flow[sink];
            total += pathcap;

            int curr = sink;
            while(curr != 0){
                int next = prevnode[curr];

                pref[next][curr] -= pathcap;
                pref[curr][next] += pathcap;
                curr = next;
            }
        }
        out.println(total);
        out.close();
    }
}