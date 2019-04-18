/*
ID: xfrostb1
LANG: JAVA
TASK: planting
*/

import java.io.*;
import java.util.*;

public class planting {
    static int max = 0, N;

    static int[] amtconnected;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
        N = Integer.parseInt(f.readLine());
        amtconnected = new int[N+1];
        for(int i = 1; i < N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            amtconnected[x]++;
            amtconnected[y]++;
        }
        int ret = 0;
        for(int i = 1; i < N+1; i++){
            ret = Math.max(amtconnected[i], ret);
        }
//        visited = new boolean[N+1];
//        dfs(1);
        out.println(ret+1);
        out.close();

    }
//    static void dfs(int index, int amt){
//        boolean[] connected = new boolean[N+1];
//        visited[index] = true;
//        for(int i = 1; i < N+1; i++){
//
//            if(conn[index][i] && index != i && !visited[i]){
//                dfs(i, );
//            }
//            if(conn[index][i] && !connected[i]){
//                amt++;
//                connected[i] = true;
//                for(int j = 1; j < N+1; j++){
//                    if(!connected[j] && conn[j][i] && j != index) {
//                        amt++;
//                        connected[j] = true;
//                    }
//                }
//
//            }
//        }
//        max = Math.max(amt, max);
//    }

}
