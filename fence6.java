/*
ID: xfrostb1
LANG: JAVA
TASK: fence6
*/

import java.io.*;
import java.util.*;

public class fence6 {
    static int N;
    static int count = 0;
    static boolean visited[][];
    static Fence[] fences;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fence6.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
        N = Integer.parseInt(f.readLine());
        fences = new Fence[N+1];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int id = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(f.readLine());
            int[] first = new int[a];
            for(int j = 0; j < a; j++){
                first[j] = Integer.parseInt(st.nextToken());
            }
            int[] second = new int[b];
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < b; j++){
                second[j] = Integer.parseInt(st.nextToken());
            }

            fences[id] = new Fence(length, first, second);
        }

        out.println(solve());
        out.close();

    }
    static int solve(){
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < N+1; i++){
            visited = new boolean[N+1][2];
            for(int dir = 0; dir <= 1; dir++){
                visited[i][dir] = true;
                min = Math.min(dfs(i, fences[i].length, i, min), min);
                visited[i][dir] = false;
            }
        }
        return min;
    }

    static int dfs(int start, int value, int line, int max){
        if(value >= max) return max;
        if(start == line && visited[start][0] && visited[start][1]){
            return value;
        }

        for (int dir = 0; dir <= 1; dir++) {
            boolean go = (line != start)? !visited[line][dir] : visited[line][dir];

            if (go) {
                visited[line][dir] = true;

                int[] neighbors;
                if(dir == 0) neighbors = fences[line].first;
                else neighbors = fences[line].second;

                for (int neighbor : neighbors) {
                    int neighborDir = fences[neighbor].firstcon(line)? 0 : 1;
                    if (!visited[neighbor][neighborDir]) {
                        visited[neighbor][neighborDir] = true;

                        int length = (neighbor != start)? value + fences[neighbor].length : value;
                        max = Math.min(max, dfs(start, length, neighbor, max));

                        visited[neighbor][neighborDir] = false;
                    }
                }

                visited[line][dir] = false;
            }
        }

        return max;

    }



    static class Fence{
        int length;
        int[] first, second;
        public Fence(int l, int[] f, int[] s){
            first = f.clone();
            second = s.clone();
            length = l;
        }
        public boolean firstcon(int a){
            for(int i : first) if(i == a) return true;

            return false;
        }
        public boolean secondcon(int a){
            for(int i : second) if(i == a) return true;

            return false;
        }
    }

}
