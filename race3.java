/*
ID: xfrostb1
LANG: JAVA
TASK: race3
*/

import java.io.*;
import java.util.*;

public class race3 {
    static int[] visitable;
    static int[] visitable2, visitedamt;
    static int N, total = 0;
    static int[][] connect;
    static boolean check = true;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("race3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));
        ArrayList<String> lines = new ArrayList<>();
        String s = f.readLine();
        while(s != null && !s.equals("-1")){
            lines.add(s);
            s = f.readLine();
        }
        N = lines.size()-1;
        connect = new int[N+1][N+1];
        visitedamt = new int[N+1];
        visitable = new int[N+1];
        visitable2 = new int[N+1];

        for(int i = 0; i < N+1; i++){
            StringTokenizer st = new StringTokenizer(lines.get(i));
            while(st.hasMoreTokens()){
                int x = Integer.parseInt(st.nextToken());
                if(x != -2 && x != i){
                    connect[i][x] = 1;
                };
            }
        }
        int last = 0;

        ArrayList<Integer> always = new ArrayList<>();
        for(int i = 1; i < N; i++){
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[N+1];

            queue.add(0);
            visited[0] = true;

            boolean valid = true;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (curr == N) {
                    valid = false;
                    break;
                }

                for (int j = 0; j < N+1; j++) {
                    if (connect[curr][j] == 1 && j != i && !visited[j]) {
                        visited[j] = true;
                        queue.add(j);
                    }
                }
            }
            if(valid) always.add(i);
        }
        ArrayList<Integer> split = new ArrayList<>();
        for(int i : always) {
            visitable2 = new int[N + 1];
            check = true;
            dfs(last, i);
            if (dfs2(i, i)) split.add(i);

            last = i;

        }

        out.print(always.size());
        for(int i : always) out.print(" "+i);
        out.print("\n");

        out.print(split.size());
        for(int i : split) out.print(" "+i);
        out.print("\n");

        out.close();
    }

    static boolean dfs2(int point, int start){
        if(visitable2[point] != 1) {
            visitable2[point] = 1;
            for (int i = 0; i < N+1; i++) {
                if (connect[point][i] != 0){
                    if(visitable[i] != 1){
                        dfs2(i, start);
                    }
                    else {
                        check = false;
                        return false;
                    }
                }
            }
        }
        if(point == start) {
            return check;
        }
        return false;
    }
    static void dfs(int point, int split){
        if(visitable[point] != 1 && point != split) {
            visitable[point] = 1;
            for (int i = 0; i < N; i++) {
                if (connect[point][i] != 0) dfs(i, split);
            }
        }
    }

}
