/*
ID: xfrostb1
LANG: JAVA
TASK: closing
*/

import java.io.*;
import java.util.*;

public class closing {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] connect = new boolean[N + 1][N + 1];
        boolean[] opened = new boolean[N + 1];
        ArrayList<Integer> disc = new ArrayList<>();
        boolean[][] sim = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            connect[x][y] = connect[y][x] = true;
        }
        String[] ret = new String[N];
        int[] order = new int[N];
        for(int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(f.readLine());
        }
        for(int i = 0; i < N; i++){
            if(i == 0){
                opened[order[N-i-1]] = true;
            }else{
                int index = order[N-i-1];
                opened[i] = true;
                boolean check = false;
                for(int j = 1; j < N; j++){
                    if(opened[j] && connect[index][j] && j != index){
                        sim[index][j] = true;
                        if(disc.contains(j)) disc.remove(disc.indexOf(j));
                        check = true;
                    }
                }
                if(!check){
                    disc.add(i);
                }

            }
            ret[N-i-1] = disc.isEmpty() ? "YES" : "NO";
        }
        for(String i : ret){
            out.println(i);
        }
        out.close();
    }
}
